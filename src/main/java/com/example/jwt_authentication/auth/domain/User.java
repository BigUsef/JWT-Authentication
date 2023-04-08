package com.example.jwt_authentication.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     * User control his account or a set of account related to one entity.
     * <br/>
     * <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Column(name = "is_enabled")
    private boolean enabled = true;

    /**
     * the platform control the account by lock down it
     * if is locked the user not able to log-in
     * <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Column(name = "is_unlocked")
    private boolean accountNonLocked = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role ->new SimpleGrantedAuthority(role.name())).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }
}

