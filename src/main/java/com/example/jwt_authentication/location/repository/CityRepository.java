package com.example.jwt_authentication.location.repository;

import com.example.jwt_authentication.location.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByLocked(boolean is_locked);
}