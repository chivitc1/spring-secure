package com.example.demo.services.impl;

import com.example.demo.models.SystemRoleType;
import com.example.demo.models.User;
import com.example.demo.services.UpdateUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@Service
@Transactional
public class UpdateUserServiceImpl implements UpdateUserService {

    private final EntityManager entityManager;

    public UpdateUserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User addSystemRoles(Integer userId, Set<SystemRoleType> roles) {
        User user = entityManager.find(User.class, userId);
        user.addRoles(roles);
        entityManager.merge(user);
        return user;
    }

    @Override
    public User removeSystemRoles(Integer userId, Set<SystemRoleType> roles) {
        User user = entityManager.find(User.class, userId);
        System.out.println("Before remove");
        System.out.println(user.getRoles());
        user.removeRoles(roles);
        System.out.println("After remove");
        System.out.println(user.getRoles());
        User user2 = entityManager.merge(user);
        System.out.println("After save");
        System.out.println(user2.getRoles());
        return user;
    }
}
