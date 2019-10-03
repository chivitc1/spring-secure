package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")

@Getter @Setter
public class User implements Principal {
    @Id
    @SequenceGenerator(name="users_generator",sequenceName="users_seq",allocationSize=1, initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getName() {
        return getEmail();
    }
}
