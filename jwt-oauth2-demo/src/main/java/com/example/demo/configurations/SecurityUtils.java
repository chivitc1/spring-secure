package com.example.demo.configurations;

import com.example.demo.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUtils {
    public static Collection<? extends GrantedAuthority> toAuthorities(Collection<Role> roles) {
        for (Role role : roles) {
            System.out.println(role.getName());
        }
        return roles.stream().map(it -> new SimpleGrantedAuthority(it.getName()))
                .collect(Collectors.toSet());
    }
}
