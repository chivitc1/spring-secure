package com.example.demo.configurations;

import com.example.demo.models.SystemRoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUtils {
    public static Collection<? extends GrantedAuthority> toAuthorities(Collection<SystemRoleType> roles) {
        return roles.stream().map(it -> new SimpleGrantedAuthority(it.name()))
                .collect(Collectors.toSet());
    }
}
