package com.gestion.demo.controller;

import com.gestion.demo.model.Todo;
import com.gestion.demo.services.TodoService;
import com.gestion.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listTodos(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false, defaultValue = "") String title,
                            @RequestParam(required = false, defaultValue = "") String username) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> todos;

        if (!title.isEmpty() || !username.isEmpty()) {
            todos = todoService.getTodosByTitleAndUsername(title, username, pageable);
        } else {
            todos = todoService.getTodos(pageable);
        }

        model.addAttribute("todos", todos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", todos.getTotalPages());
        model.addAttribute("title", title);
        model.addAttribute("username", username);
        return "list";
    }

    @GetMapping("/create")
    public String createTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("users", userService.findAllUsers());
        return "todo-form";
    }

    @PostMapping("/save")
    public String saveTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            model.addAttribute("todo", todo);
            model.addAttribute("users", userService.findAllUsers());
            return "todo-form";
        }
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}
