package com.example.demo.web.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter @Builder
public class UserResponse {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> roles;
}
