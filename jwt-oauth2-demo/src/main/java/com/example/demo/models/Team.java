package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @SequenceGenerator(name="teams_generator",sequenceName="teams_seq",allocationSize=1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teams_generator")
    private Integer id;

    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamUser> users = new ArrayList<>();

    public void addUser(User user, TeamRoleType role) {
        TeamUser teamUser = new TeamUser(this, user, role);
        this.users.add(teamUser);
        user.getTeams().add(teamUser);
    }

    public void removeUser(User user) {
        for (Iterator<TeamUser> iterator = this.users.iterator(); iterator.hasNext(); ) {
            TeamUser teamUser = iterator.next();
            if (userExists(user, teamUser)) {
                iterator.remove();
                teamUser.getUser().getTeams().remove(teamUser);
                teamUser.setTeam(null);
                teamUser.setUser(null);
            }
        }
    }

    private boolean userExists(User user, TeamUser teamUser) {
        return teamUser.getTeam().equals(this) && teamUser.getUser().equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return code.equals(team.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
