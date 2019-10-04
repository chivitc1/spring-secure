package com.example.demo.web.model;

import com.example.demo.models.SystemRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class NewUserForm {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Set<SystemRoleType> roles = new HashSet<>();
}
