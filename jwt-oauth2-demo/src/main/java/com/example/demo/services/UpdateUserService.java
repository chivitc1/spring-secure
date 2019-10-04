package com.example.demo.services;

import com.example.demo.models.SystemRoleType;
import com.example.demo.models.User;

import java.util.Set;

public interface UpdateUserService {

    User addSystemRoles(Integer userId, Set<SystemRoleType> roles);
    User removeSystemRoles(Integer userId, Set<SystemRoleType> roles);
}
