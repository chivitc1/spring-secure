package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team_users")
@Getter
@Setter
public class TeamUser {
    @EmbeddedId
    private TeamUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private TeamRoleType role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamUser teamUser = (TeamUser) o;
        return team.equals(teamUser.team) &&
                user.equals(teamUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, user);
    }

    public TeamUser() {
        // for entity config
    }

    public TeamUser(Team team, User user, TeamRoleType role) {
        this.team = team;
        this.user = user;
        this.id = new TeamUserId(team.getId(), user.getId());
        this.role = role;
    }
}
