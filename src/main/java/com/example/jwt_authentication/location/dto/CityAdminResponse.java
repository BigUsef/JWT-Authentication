package com.example.jwt_authentication.location.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CityAdminResponse {
    private Long id;
    private String nameEn;
    private String nameAr;

    @JsonProperty("isLocked")
    private boolean locked;
}
