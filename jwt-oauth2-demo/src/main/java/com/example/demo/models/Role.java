package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(name="roles_generator",sequenceName="roles_seq",allocationSize=1, initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_generator")
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role(String roleName) {
        this.name = roleName;
    }
}
