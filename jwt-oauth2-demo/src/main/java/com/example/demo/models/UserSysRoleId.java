//package com.example.demo.models;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//@Getter
//@Setter
//public class UserSysRoleId implements Serializable {
//    @Column(name = "user_id", nullable = false, insertable = false, updatable=false)
//    private Integer userId;
//
//    @Column(name = "role", nullable = false, insertable = false, updatable=false)
//    private String role;
//
//    public SystemRoleType getRole() {
//        return SystemRoleType.valueOf(role);
//    }
//
//    public UserSysRoleId() {}
//    public UserSysRoleId(Integer userId, SystemRoleType role) {
//        this.userId = userId;
//        this.role = role.name();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserSysRoleId that = (UserSysRoleId) o;
//        return userId.equals(that.userId) &&
//                role == that.role;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, role);
//    }
//}
