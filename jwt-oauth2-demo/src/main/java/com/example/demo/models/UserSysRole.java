package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserSysRole implements Serializable {
    @Id
    @SequenceGenerator(name="user_roles_id_generator",sequenceName="user_roles_id_seq",allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_id_generator")
    private Integer id;

    @Column(name = "role")
    private String role;

    @ManyToOne
    private User user;

    public SystemRoleType getRole() {
        return SystemRoleType.valueOf(role);
    }

    public UserSysRole() {}
    public UserSysRole(User user, SystemRoleType role) {
        this.user = user;
        this.role = role.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSysRole sysRole = (UserSysRole) o;
        return user.getId().equals(sysRole.getUser().getId()) &&
                role.equals(sysRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.user.getId(),
                role
//                SystemRoleType.valueOf(role).hashCode()
        );
    }
}
