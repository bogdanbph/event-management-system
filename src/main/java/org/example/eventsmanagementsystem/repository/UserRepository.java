package org.example.eventsmanagementsystem.repository;

import org.example.eventsmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
}
