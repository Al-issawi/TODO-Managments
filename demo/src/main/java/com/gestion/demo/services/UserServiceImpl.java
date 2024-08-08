package com.gestion.demo.services;

import com.gestion.demo.model.Todo;
import com.gestion.demo.model.User;
import com.gestion.demo.repository.TodoRepository;
import com.gestion.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    // Injecting UserRepository and TodoRepository dependencies
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    // Method to get a paginated list of users
    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // Method to get a user by their ID
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Method to get todos by title and username with pagination
    @Override
    public Page<Todo> getTodosByTitleAndUsername(String title, String username, Pageable pageable) {
        return todoRepository.findByTitleContainingAndUser_Username(title, username, pageable);
    }

    // Method to get a user by their username
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to find all users
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Method to save a new user
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Method to update an existing user by their ID
    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setAddress(updatedUser.getAddress());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Method to delete an existing user by their ID
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
