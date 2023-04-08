package com.example.jwt_authentication.location.dto;

import com.example.jwt_authentication.location.domain.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;

@Mapper(imports = {LocaleContextHolder.class})
public interface CityMapper {

    @Mapping(target = "id", ignore = true)
    City toCity(CityForm cityForm);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget City city, CityForm cityForm);

    CityAdminResponse toAdminResponse(City city);

    @Mapping(target = "name", expression = "java(LocaleContextHolder.getLocale().getLanguage().equals(\"ar\") ? city.getNameAr() : city.getNameEn())")
    CityResponse toResponse(City city);

    List<CityResponse> toListResponse(List<City> cityPage);
}
