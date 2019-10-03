package com.example.demo.web.controllers.admin;

import com.example.demo.configurations.AuthorityConstants;
import com.example.demo.services.UserService;
import com.example.demo.web.model.NewUserForm;
import com.example.demo.web.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Secured({ AuthorityConstants.ROLE_ADMIN })
    public ResponseEntity<UserResponse> create(@RequestBody NewUserForm newUser) {
        UserResponse result = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
