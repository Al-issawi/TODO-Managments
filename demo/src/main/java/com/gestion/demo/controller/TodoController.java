package com.gestion.demo.controller;

import com.gestion.demo.model.Todo;
import com.gestion.demo.services.TodoService;
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

 //* Controller class for handling Todo-related web requests.

@Controller
@RequestMapping("/todos")
public class TodoController {

    /*
     *Injecting TodoService dependency
     */
    @Autowired
    private TodoService todoService;

    /*
     *Injecting UserService dependency
     */
    @Autowired
    private UserService userService;

   /*
    *Handles GET requests to retrieve a paginated list of todos.
   */

    @GetMapping
    public String getAllTodos(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Todo> todoPage = todoService.getTodos(pageable);
        model.addAttribute("todoPage", todoPage);
        return "todos";
    }

   /* Handles GET requests to show the form for creating a new todo.
    * model the model to add attributes to
    * and return the view name for displaying the create todo form
   */
   @GetMapping("/create")

   public String showCreateTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("users", userService.getUsers(PageRequest.of(0, 10)).getContent());
        return "create-todo";
    }

    /* Handles POST requests to create a new todo.
     * todo the todo entity to be created
     * bindingResult the result of validation checks
     * model the model to add attributes to
     * to return the redirect URL after creating the todo
     */
    @PostMapping("/create")
    public String createTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getUsers(PageRequest.of(0, 10)).getContent());
            return "create-todo";
        }
        todoService.saveTodo(todo);
        return "redirect:/todos";
    }

    /*
     * Handles GET requests to show the form for editing an existing todo.
     * id the ID of the todo to be edited
     * model the model to add attributes to
     * the view name for displaying the edit todo form
     */
    @GetMapping("/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            model.addAttribute("todo", todo.get());
            model.addAttribute("users", userService.getUsers(PageRequest.of(0, 10)).getContent());
            return "edit-todo";
        } else {
            return "redirect:/todos";
        }
    }

    /*
     * Handles POST requests to update an existing todo.
     *
     * id the ID of the todo to be updated
     * todo the updated todo entity
     * bindingResult the result of validation checks
     * model the model to add attributes to
     * the redirect URL after updating the todo
     */
    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getUsers(PageRequest.of(0, 10)).getContent());
            return "edit-todo";
        }
        todoService.updateTodo(id, todo);
        return "redirect:/todos";
    }

    /*
     * Handles POST requests to delete a todo.
     * id the ID of the todo to be deleted
     * the redirect URL after deleting the todo
     */

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}
