package com.example.demo.services.impl;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserBizUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserBizUpdateServiceImpl implements UserBizUpdateService {
    @Autowired
    private final UserRepository userRepository;

    public UserBizUpdateServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer updateRectrictFieldOwner(Integer earnValue) {
        String principal = getCurrentUsername();
        User targetUser = userRepository.findByEmail(principal).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldOwner(earnValue + targetUser.getRectrictFieldOwner());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldOwner();
    }

    private String getCurrentUsername() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Integer updateRectrictFieldSysadm(Integer userId, Integer earnValue) {
        return null;
    }

    @Override
    public Integer updateRectrictFieldMentor(Integer userId, Integer earnValue) {
        return null;
    }

    @Override
    public Integer updateRectrictFieldTeamLeader(Integer userId, Integer earnValue) {
        return null;
    }

    @Override
    public Integer updateRectrictFieldTeamAdmin(Integer userId, Integer earnValue) {
        return null;
    }
}
