package com.example.demo;

import com.example.demo.models.SystemRoleType;
import com.example.demo.models.Team;
import com.example.demo.models.TeamRoleType;
import com.example.demo.models.User;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
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
        userRepository.save(user1);

        // add more roles
        Set<SystemRoleType> systemRoles = new HashSet<>();
        systemRoles.add(SystemRoleType.ROLE_SYSTEM);
        systemRoles.add(SystemRoleType.ROLE_SYSTEM_ADMIN);
        user1.addRoles(systemRoles);
        userRepository.save(user1);
    }
}
