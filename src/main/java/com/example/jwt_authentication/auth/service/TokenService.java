package com.example.jwt_authentication.auth.service;

import com.example.jwt_authentication.auth.domain.User;
import com.example.jwt_authentication.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${zouba.jwt.access-expiry}")
    private int accessExpiryTime;
    @Value("${zouba.jwt.refresh-expiry}")
    private int refreshExpiryTime;

    private final JwtEncoder encoder;

    private String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        User user = (User) authentication.getPrincipal();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Zouba Application")
                .issuedAt(now)
                .expiresAt(now.plus(accessExpiryTime, ChronoUnit.MINUTES))
                .subject(user.getId().toString())
                .claim("scope", user.getAuthorities().toString())
                .build();

        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims);
        return encoder.encode(encoderParameters).getTokenValue();
    }

    private String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();
        User user = (User) authentication.getPrincipal();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("Zouba Application")
                .issuedAt(now)
                .expiresAt(now.plus(refreshExpiryTime, ChronoUnit.DAYS))
                .subject(user.getId().toString())
                .build();

        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(encoderParameters).getTokenValue();
    }

    public TokenResponse createToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof User user)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setId(user.getId());
        tokenResponse.setAccessToken(generateAccessToken(authentication));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = generateRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = generateRefreshToken(authentication);
        }
        tokenResponse.setRefreshToken(refreshToken);

        return tokenResponse;
    }
}
