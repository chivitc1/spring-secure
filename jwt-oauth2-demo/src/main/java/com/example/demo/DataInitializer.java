package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.OAuth2ClientRepository;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DataInitializer {

    @Autowired
    private OAuth2ClientRepository clientRepository;
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
        Team teamB = new Team();
        teamB.setCode("TEAM_B");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        // Setup team A
        List<User> users = new ArrayList<>();
        for (int i=1; i<=5; i++) {
            User user = User.addNewUser("user" + i + "@example.com", passwordEncoder.encode("123456"));
            users.add(user);
        }
        userRepository.saveAll(users);
        for (User user : users) {
            teamA.addUser(user, TeamRoleType.MEMBER);
        }
        teamRepository.save(teamA);
        User leader1 = User.addNewUser("leader1@example.com", passwordEncoder.encode("123456"));
        User leader2 = User.addNewUser("leader2@example.com", passwordEncoder.encode("123456"));
        User admin1 = User.addNewUser("admin1@example.com", passwordEncoder.encode("123456"));
        userRepository.saveAll(Arrays.asList(leader1, leader2, admin1));
        teamA.addUser(leader1, TeamRoleType.LEADER);
        teamA.addUser(leader2, TeamRoleType.LEADER);
        teamA.addUser(admin1, TeamRoleType.ADMIN);
        teamRepository.save(teamA);

        // Setup teamB
        User user1B = User.addNewUser("user1b@example.com", passwordEncoder.encode("123456"));
        User leader1B = User.addNewUser("leader1b@example.com", passwordEncoder.encode("123456"));
        User admin1B = User.addNewUser("admin1b@example.com", passwordEncoder.encode("123456"));
        userRepository.saveAll(Arrays.asList(user1B, leader1B, admin1B));
        teamB.addUser(user1B, TeamRoleType.MEMBER);
        teamB.addUser(leader1B, TeamRoleType.LEADER);
        teamB.addUser(admin1B, TeamRoleType.ADMIN);
        teamRepository.save(teamB);

        // create test user
        User testUser = new User();
        testUser.setEmail("testuser@example.com");
        testUser.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(testUser);

        // add users to teams
        teamA.addUser(testUser, TeamRoleType.MEMBER);
        teamRepository.save(teamA);

        // grant role to user
        testUser.addRole(SystemRoleType.ROLE_USER);
        UserSysRole userSysRole = new UserSysRole();
        userSysRole.setUser(testUser);
        userSysRole.setRole(SystemRoleType.ROLE_USER.name());
        userRepository.save(testUser);

        // add more roles
        Set<SystemRoleType> roles = new HashSet<>();
        roles.add(SystemRoleType.ROLE_SYSTEM);
        roles.add(SystemRoleType.ROLE_SYSTEM_ADMIN);
        userService.addSystemRoles(testUser.getId(), roles);

        Set<SystemRoleType> roles2 = new HashSet<>();
        roles2.add(SystemRoleType.ROLE_USER);
        userService.addSystemRoles(testUser.getId(), roles2);

        // remove role
        Set<SystemRoleType> roles3 = new HashSet<>();
        roles3.add(SystemRoleType.ROLE_SYSTEM);
        userService.removeSystemRoles(testUser.getId(), roles3);

        // set default system role
        List<User> unsetRoleUsers = userRepository.findByRolesIsEmpty();
        for (User user : unsetRoleUsers) {
            user.addRole(SystemRoleType.ROLE_USER);
        }
        userRepository.saveAll(unsetRoleUsers);

        // add oauth2 clients
        OAuth2Client client1 = new OAuth2Client(passwordEncoder.encode("123456"));
        clientRepository.save(client1);
    }
}
