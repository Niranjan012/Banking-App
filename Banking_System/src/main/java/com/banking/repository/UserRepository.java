package com.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// Custom query to find a user by username
    Optional<User> findByUsername(String username);

    // Custom query to find a user by email
    Optional<User> findByEmail(String email);

    // Custom query to check if a user exists by username
    Boolean existsByUsername(String username);

    // Custom query to check if a user exists by email
    Boolean existsByEmail(String email);
}
