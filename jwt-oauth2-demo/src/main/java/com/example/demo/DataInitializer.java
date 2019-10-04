package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UpdateUserService userService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        // create teams
        Team teamA = new Team();
        teamA.setCode("TEAM_A");
        teamRepository.save(teamA);

        // create users
        User user1 = new User();
        user1.setEmail("user1@example.com");
        user1.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user1);

        // add users to teams
        teamA.addUser(user1, TeamRoleType.MEMBER);
        teamRepository.save(teamA);

        // grant role to user
        user1.addRole(SystemRoleType.ROLE_USER);
        UserSysRole userSysRole = new UserSysRole();
        userSysRole.setUser(user1);
        userSysRole.setRole(SystemRoleType.ROLE_USER.name());
        userRepository.save(user1);

        // add more roles
        Set<SystemRoleType> roles = new HashSet<>();
        roles.add(SystemRoleType.ROLE_SYSTEM);
        roles.add(SystemRoleType.ROLE_SYSTEM_ADMIN);
        userService.addSystemRoles(user1.getId(), roles);

        Set<SystemRoleType> roles2 = new HashSet<>();
        roles2.add(SystemRoleType.ROLE_USER);
        userService.addSystemRoles(user1.getId(), roles2);

        // remove role
        Set<SystemRoleType> roles3 = new HashSet<>();
        roles3.add(SystemRoleType.ROLE_SYSTEM);
        userService.removeSystemRoles(user1.getId(), roles3);
    }
}
