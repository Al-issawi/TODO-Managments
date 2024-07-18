package com.gestion.demo.services;

import com.gestion.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/*
 * Service interface for User entities.
 * Provides methods for CRUD operations and pagination.
 */
public interface UserService {


    Page<User> getUsers(Pageable pageable);

    //* Retrieves a user by their ID.
    Optional<User> getUserById(Long id);

    //TODO method findbyName (username).
    Optional<User> findByName(String username);

    User saveUser(User user);

    User updateUser(Long id, User user);

    // Method to Delete an existing user by their ID
    void deleteUserById(Long id);

    //TODO Methos find byName (username)
   /*
    @GetMapping("/findByName")

    String findByName(@RequestParam("name") String username, Model model);

    void deleteUserById(Long id);

   */
}