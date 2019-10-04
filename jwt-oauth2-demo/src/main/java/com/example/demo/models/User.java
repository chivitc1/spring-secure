package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter @Setter
public class User implements Principal {
    @Id
    @SequenceGenerator(name="users_generator",sequenceName="users_seq",allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    private Integer id;

    private String email;
    private String password;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "user_roles")
    private Set<String> roles = new HashSet<>();

    public Set<SystemRoleType> getRoles() {
        return roles.stream().map(it -> SystemRoleType.valueOf(it)).collect(Collectors.toSet());
    }

    @OneToMany(mappedBy = "user")
    private List<TeamUser> teams = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supervisor_id")
    private User supervisor;

    @Override
    public String getName() {
        return getEmail();
    }

    public Optional<User> getUser() {
        return Optional.ofNullable(this.supervisor);
    }

    public void addRole(SystemRoleType role) {
        this.roles.add(role.name());
    }

    public void addRoles(Set<SystemRoleType> roles) {
        this.roles.addAll(roles.stream().map(it -> it.name()).collect(Collectors.toSet()));
    }

    public void removeRoles(Set<SystemRoleType> roles) {
        for (SystemRoleType role : roles) {
            this.roles.remove(role.name());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
