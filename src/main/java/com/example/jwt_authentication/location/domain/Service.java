package com.example.jwt_authentication.location.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_gen")
    @SequenceGenerator(name = "services_gen", sequenceName = "services_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_en", nullable = false)
    private String nameEn;
    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @Column(name = "is_limited")
    private boolean limited = false;
    @Column(name = "is_locked")
    private boolean locked = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
