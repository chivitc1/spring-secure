package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u from User u join fetch u.roles")
    List<User> findAll();

    List<User> findByRolesIsEmpty();

    @Query("SELECT u from User u join fetch u.mentor where u.id = :userId")
    User getUserWithMentorByUserId(@Param("userId") Integer userId);
}
