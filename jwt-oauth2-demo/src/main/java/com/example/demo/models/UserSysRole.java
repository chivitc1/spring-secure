//package com.example.demo.models;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Entity
//@Table(name = "user_roles")
//@Getter
//@Setter
//public class UserSysRole {
//    @EmbeddedId
//    private UserSysRoleId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
//    private User user;
//
//    @MapsId("role")
//    @Column(name = "role", insertable=false, nullable = false)
//    private String role;
//
//    public SystemRoleType getRole() {
//        return SystemRoleType.valueOf(role);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserSysRole that = (UserSysRole) o;
//        return user.equals(that.user) &&
//                role == that.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, role);
//    }
//
//    public UserSysRole(){}
//    public UserSysRole(User user, SystemRoleType role) {
//        this.id = new UserSysRoleId(user.getId(), role);
//        this.user = user;
//        this.role = role.name();
//    }
//}
