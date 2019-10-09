package com.example.demo.services;

import com.example.demo.configurations.permissions.MentorPermission;
import com.example.demo.configurations.permissions.OwnerPermission;
import com.example.demo.configurations.permissions.SystemRolePermission;
import com.example.demo.configurations.permissions.TeamLeaderPermission;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserBizService {
    /**
     * only owner or SYSTEM, SYSTEM_ADMIN can do
     *
     * @param userId
     * @param earnValue
     * @return
     */
    @OwnerPermission
    Integer updateRectrictFieldOwner(Integer userId, Integer earnValue);

    /**
     * Only SYSTEM_ADMIN, SYSTEM can do
     * @param userId
     * @param earnValue
     * @return
     */
    @SystemRolePermission
    Integer updateRectrictFieldSysadm(Integer userId, Integer earnValue);

    /**
     * Only mentor of user can do
     * @param userId
     * @param earnValue
     * @return
     */
    @MentorPermission
    Integer updateRectrictFieldMentor(Integer userId, Integer earnValue);

    /**
     * Only SYSTEM, SYSTEM_ADMIN or team role LEADER or ADMIN of the teams own resource userId can do
     * @param userId
     * @param earnValue
     * @return
     */
    @TeamLeaderPermission
    Integer updateRectrictFieldTeamLeader(Integer userId, Integer earnValue);

    /**
     * Only ADMIN of the team owns resource userId can do
     * @param userId
     * @param earnValue
     * @return
     */
    @PreAuthorize("@userAuthorityService.hasTeamRole(#userId, 'ADMIN')")
    Integer updateRectrictFieldTeamAdmin(Integer userId, Integer earnValue);
}
