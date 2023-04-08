package com.example.jwt_authentication.location.controller;

import com.example.jwt_authentication.location.domain.City;
import com.example.jwt_authentication.location.dto.CityAdminResponse;
import com.example.jwt_authentication.location.dto.CityForm;
import com.example.jwt_authentication.location.dto.CityMapper;
import com.example.jwt_authentication.location.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/utilities/city")
public class CityController {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping
    public Page<CityAdminResponse> getAllCities(@PageableDefault final Pageable pageable){
        return cityService
                .getAllWithPagination(pageable)
                .map(cityMapper::toAdminResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityAdminResponse addNewCity(@RequestBody CityForm cityForm) {
        City city = cityService.add(cityForm);
        return cityMapper.toAdminResponse(city);
    }

    @PutMapping(path = "/{city_id}")
    public CityAdminResponse updateCity(@PathVariable("city_id") long id, @RequestBody CityForm cityForm) {
        City city = cityService.update(id, cityForm);
        return cityMapper.toAdminResponse(city);
    }

    @DeleteMapping(path = "/{city_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable("city_id") long id) {
        cityService.delete(id);
    }
}
