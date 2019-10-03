package com.example.demo.web.model;

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
    private Set<String> roles = new HashSet<>();
}
