package com.example.demo.configurations.permissions;

import com.example.demo.models.TeamUser;
import com.example.demo.models.User;
import com.example.demo.repositories.TeamUserRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userAuthorityService")
public class UserAuthorityService {
    @Autowired
    private TeamUserRepository teamUserRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean isResourceOwner(Integer userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userId.equals(currentUser.getId());
    }

    public boolean isMentorOfUser(Integer userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User resource = userRepository.getUserWithMentorByUserId(userId);
        if (resource == null) return false;
        User mentor = resource.getMentor();
        return mentor != null && mentor.getId().equals(currentUser.getId());
    }

    public boolean hasTeamRole(Integer userId, String teamRole) {
        List<Integer> resourceTeamIds = getTeamIdsOfResource(userId);
        List<TeamUser> teamsOfCurrentUserWithProvidedRole = getUserTeamsWithRole(teamRole);

        Boolean result = teamsOfCurrentUserWithProvidedRole.stream().anyMatch(teamUser -> resourceTeamIds.contains(teamUser.getTeam().getId()));
        return result;
    }

    private List<TeamUser> getUserTeamsWithRole(String teamRole) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getTeams().stream().filter(it -> it.getRole().name().equals(teamRole)).collect(Collectors.toList());
    }

    private List<Integer> getTeamIdsOfResource(Integer resourceId) {
        return teamUserRepository.findByUserId(resourceId).stream().map(it -> it.getTeam().getId()).collect(Collectors.toList());
    }
}
