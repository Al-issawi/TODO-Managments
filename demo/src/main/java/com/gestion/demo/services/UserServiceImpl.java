package com.gestion.demo.services;

import com.gestion.demo.model.User;
import com.gestion.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    // Injecting UserRepository dependency
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public Optional<User> findByName(String username) {
        return Optional.empty();
    }

    // Method to save a new  user
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /*
     TODO method findbyName (username).
    @Override

    public Optional<User> findByName(String username) {

        return userRepository.findByName(username);
    }
    @Override
    public String findByName(String username, Model model) {
        return username;
    }



*/

 /*   @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAddress(user.getAddress());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
**/

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




    // Method to Delete an existing user by their ID
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
