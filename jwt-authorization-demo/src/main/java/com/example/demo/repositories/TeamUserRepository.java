package com.example.demo.repositories;

import com.example.demo.models.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamUserRepository extends JpaRepository<TeamUser, Integer> {
    List<TeamUser> findByUserId(Integer userId);
}
