package com.example.jwt_authentication.auth.controller;

import com.example.jwt_authentication.auth.dto.SignInRequest;
import com.example.jwt_authentication.auth.dto.TokenResponse;
import com.example.jwt_authentication.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/sign-in")
    public TokenResponse logIn(@RequestBody SignInRequest signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInForm.getEmail(),
                        signInForm.getPassword()
                )
        );
        return tokenService.createToken(authentication);
    }

    @GetMapping("/refresh")
    public String refreshToken() {
        return "";
    }
}
