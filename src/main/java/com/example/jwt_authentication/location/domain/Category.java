package com.example.jwt_authentication.location.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_gen")
    @SequenceGenerator(name = "categories_gen", sequenceName = "categories_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_en", nullable = false)
    private String nameEn;
    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @Column(name = "is_locked")
    private boolean locked = false;
}