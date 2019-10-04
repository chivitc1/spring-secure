package com.example.demo.services.impl;

import com.example.demo.models.SystemRoleType;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.web.model.NewUserForm;
import com.example.demo.web.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import com.example.demo.configurations.UserContextHolder;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username );
    }

    @Override
    public UserResponse createUser(NewUserForm newUser) {
//        UserContext userContext = UserContextHolder.getContext();
//        log.info("UserService - createUser() - current user: userId = {}", userContext.getUserId());
        User user = new User();
        user.setEmail(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        user.addRoles(newUser.getRoles());

        if (user.getRoles().isEmpty()) {
            user.addRole(SystemRoleType.ROLE_USER);
        }
        userRepository.save(user);

        UserResponse result = UserResponse.builder()
                .id(user.getId())
                .username(user.getEmail())
                .roles(user.getRoles())
                .build();
        return result;
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> result = users.stream().map(it -> UserResponse.builder()
                .id(it.getId())
                .username(it.getEmail())
                .roles(it.getRoles())
                .build()).collect(Collectors.toList());
        return result;
    }
}
