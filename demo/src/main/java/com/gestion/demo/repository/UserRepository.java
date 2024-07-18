package com.gestion.demo.repository;

import com.gestion.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Repository interface for User entities.
 * Extends JpaRepository and PagingAndSortingRepository to provide CRUD operations
 * and pagination and sorting capabilities.
 */
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    // Method to find a user by their username
    Optional<User> findByName(String username);
}
