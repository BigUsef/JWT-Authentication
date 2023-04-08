package com.example.jwt_authentication.auth.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TokenResponse {
    private UUID id;
    private String accessToken;
    private String refreshToken;
}
