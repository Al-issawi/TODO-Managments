package com.gestion.demo.controller;

import com.gestion.demo.model.User;
import com.gestion.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<User> userPage = userService.getUsers(pageable);
        model.addAttribute("userPage", userPage);
        return "users";
    }

    // TODO findeByName userName
    /*
    @GetMapping("/findByName")
    public String findByName(@RequestParam("username") String username, Model model) {
        Optional<User> user = userService.findByName(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-details"; // Make sure to create this template to display user details
        } else {
            model.addAttribute("message", "User not found");
            return "error"; // Make sure to create this template to display errors
        }
    }
    * */

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-user";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "edit-user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
