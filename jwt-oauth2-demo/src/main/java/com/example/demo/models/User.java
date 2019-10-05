package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Principal {
    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    private Integer id;

    private String email;
    private String password;

    private Integer rectrictFieldOwner = 0;     // only owner & sysadm can do
    private Integer rectrictFieldSysadm = 0;    // only sysadm can do
    private Integer rectrictFieldMentor = 0; //user which is mentor of the user can do
    private Integer rectrictFieldTeamLeader = 0; // Team leader, team admin and sysadm can do
    private Integer rectrictFieldTeamAdmin = 0; // Team admin and sysadm can do

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private Set<UserSysRole> roles = new HashSet<>();

    public Set<SystemRoleType> getRoles() {
        return roles.stream().map(it -> it.getRole()).collect(Collectors.toSet());
    }

    public void setRoles(Set<SystemRoleType> roles) {
        this.roles.addAll(roles.stream().map(it -> new UserSysRole(this, it)).collect(Collectors.toSet()));
    }

    @OneToMany(mappedBy = "user")
    private List<TeamUser> teams = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @Override
    public String getName() {
        return getEmail();
    }


    public Optional<User> getUser() {
        return Optional.ofNullable(this.mentor);
    }

    public void addRole(SystemRoleType role) {
        UserSysRole sysRole = new UserSysRole(this, role);
        this.roles.add(sysRole);
    }

    public void addRoles(Set<SystemRoleType> roles) {
        this.roles.addAll(roles.stream().map(it -> new UserSysRole(this, it)).collect(Collectors.toSet()));
    }

    public void removeRoles(Set<SystemRoleType> roles) {
        for (SystemRoleType role : roles) {
            this.roles.remove(new UserSysRole(this, role));
        }
    }

    public static User addNewUser(String email, String encodedPassword) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        return user;
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
