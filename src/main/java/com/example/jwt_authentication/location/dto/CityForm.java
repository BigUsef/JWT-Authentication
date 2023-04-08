package com.example.jwt_authentication.location.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CityForm {
    @NotEmpty
    private String nameEn;
    @NotEmpty
    private String nameAr;

    @JsonProperty("isLocked")
    private boolean locked = false;
}
