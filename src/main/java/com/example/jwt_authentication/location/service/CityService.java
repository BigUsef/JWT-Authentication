package com.example.jwt_authentication.location.service;

import com.example.jwt_authentication.location.domain.City;
import com.example.jwt_authentication.location.dto.CityForm;
import com.example.jwt_authentication.location.dto.CityMapper;
import com.example.jwt_authentication.location.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<City> getAllActive() {
        return cityRepository.findAllByLocked(false);
    }

    public Page<City> getAllWithPagination (Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public City add(CityForm form) {
        City city = cityMapper.toCity(form);
        return cityRepository.save(city);
    }

    public City update(Long cityId, CityForm form) {
        City city = cityRepository.findById(cityId).orElseThrow();
        cityMapper.update(city, form);
        return cityRepository.saveAndFlush(city);
    }

    public void delete(long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow();
        cityRepository.delete(city);
    }
}
