package com.example.jwt_authentication.location.controller;

import com.example.jwt_authentication.location.domain.City;
import com.example.jwt_authentication.location.dto.CityMapper;
import com.example.jwt_authentication.location.dto.CityResponse;
import com.example.jwt_authentication.location.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/utilities")
public class UtilityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping("/cities")
    public List<CityResponse> getActiveCities() {
        List<City> cities = cityService.getAllActive();
        return cityMapper.toListResponse(cities);
    }
}
