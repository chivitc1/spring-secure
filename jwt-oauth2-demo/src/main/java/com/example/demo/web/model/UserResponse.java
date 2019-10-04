package com.example.demo.web.model;

import com.example.demo.models.SystemRoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter @Builder
public class UserResponse {
    private Integer id;
    private String username;
    private Set<SystemRoleType> roles;
}
