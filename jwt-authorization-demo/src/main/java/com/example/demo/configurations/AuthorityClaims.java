package com.example.demo.configurations;

import com.example.demo.models.TeamRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter @Setter
public class AuthorityClaims {
    private Collection<String> roles;
    private Collection<TeamRole> teamRoles;

    @AllArgsConstructor @Getter
    protected static class TeamRole {
        private Integer teamId;
        private TeamRoleType role;
    }
}
