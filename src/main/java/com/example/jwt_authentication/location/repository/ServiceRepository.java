package com.example.jwt_authentication.location.repository;

import com.example.jwt_authentication.location.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findAllByLocked(boolean is_locked);
}