package com.gestion.demo.services;

import com.gestion.demo.model.Todo;
import com.gestion.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<Todo> getTodosByTitleAndUsername(String title, String username, Pageable pageable);

    Optional<User> getUserByUsername(String username);
        User saveUser(User user);

    Page<User> getUsers(Pageable pageable);

    Optional<User> getUserById(Long id);



    User updateUser(Long id, User updatedUser);

    void deleteUserById(Long id);

    List<User> findAllUsers();
}



