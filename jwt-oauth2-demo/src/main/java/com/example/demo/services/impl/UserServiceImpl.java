package com.example.demo.services.impl;

import com.example.demo.configurations.UserContext;
//import com.example.demo.configurations.UserContextHolder;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.web.model.NewUserForm;
import com.example.demo.web.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());

        for (String roleName : newUser.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException(String.format("Role name %s is not found", roleName)));
            user.getRoles().add(role);
        }

        if (user.getRoles().isEmpty()) {
            Role standardRole = roleRepository.findByName("ROLE_USER").get();
            user.getRoles().add(standardRole);
        }
        userRepository.save(user);

        UserResponse result = UserResponse.builder()
                .id(user.getId())
                .username(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles().stream().map(it -> it.getName()).collect(Collectors.toSet()))
                .build();
        return result;
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> result = users.stream().map(it -> UserResponse.builder()
                .id(it.getId())
                .username(it.getEmail())
                .firstName(it.getFirstName())
                .lastName(it.getLastName())
                .roles(it.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
        return result;
    }
}
