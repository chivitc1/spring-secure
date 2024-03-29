package com.example.demo.services;

public interface UserBizUpdateService {
    /**
     * only owner can do
     *
     * @param userId
     * @param earnValue
     * @return
     */
    Integer updateRectrictFieldOwner(Integer userId, Integer earnValue);
    Integer updateRectrictFieldSysadm(Integer userId, Integer earnValue);
    Integer updateRectrictFieldMentor(Integer userId, Integer earnValue);
    Integer updateRectrictFieldTeamLeader(Integer userId, Integer earnValue);
    Integer updateRectrictFieldTeamAdmin(Integer userId, Integer earnValue);
}
