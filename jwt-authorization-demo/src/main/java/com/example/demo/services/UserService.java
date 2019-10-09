package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.web.model.NewUserForm;
import com.example.demo.web.model.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    UserResponse createUser(NewUserForm newUser);
    List<UserResponse> getUsers();
}
