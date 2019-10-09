package com.example.demo.services.impl;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBizServiceImpl implements UserBizService {
    @Autowired
    private final UserRepository userRepository;
    public UserBizServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer updateRectrictFieldOwner(Integer userId, Integer earnValue) {
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldOwner(earnValue + targetUser.getRectrictFieldOwner());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldOwner();
    }

    @Override
    public Integer updateRectrictFieldSysadm(Integer userId, Integer earnValue) {

        User targetUser = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldSysadm(earnValue + targetUser.getRectrictFieldSysadm());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldSysadm();
    }

    @Override
    public Integer updateRectrictFieldMentor(Integer userId, Integer earnValue) {
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldMentor(earnValue + targetUser.getRectrictFieldMentor());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldMentor();
    }

    @Override
    public Integer updateRectrictFieldTeamLeader(Integer userId, Integer earnValue) {

        User targetUser = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldTeamLeader(earnValue + targetUser.getRectrictFieldTeamLeader());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldTeamLeader();
    }

    @Override
    public Integer updateRectrictFieldTeamAdmin(Integer userId, Integer earnValue) {
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("NOT FOUND"));
        targetUser.setRectrictFieldTeamAdmin(earnValue + targetUser.getRectrictFieldTeamAdmin());
        userRepository.save(targetUser);
        return targetUser.getRectrictFieldTeamAdmin();
    }
}
