package com.example.demo.web.controllers;

import com.example.demo.configurations.AuthorityConstants;
import com.example.demo.services.UserService;
import com.example.demo.web.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Secured({ AuthorityConstants.ROLE_USER })
    public List<UserResponse> list() {
        List<UserResponse> result = userService.getUsers();
        return result;
    }
}
