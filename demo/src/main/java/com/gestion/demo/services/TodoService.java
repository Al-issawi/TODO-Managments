package com.gestion.demo.services;

import com.gestion.demo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
/**
 * Service interface for Todo entities.
 * Provides methods for CRUD operations and pagination.
 * TODO  (params)
 */
public interface TodoService {
    /**
     * Retrieves a paginated list of todos.
     */

    Page<Todo> getTodos(Pageable pageable);
    /**
     * Retrieves a todo by its ID.
     *
     */
    Optional<Todo> getTodoById(Long id);

    /*TDODO findByName username Method
    Optional<User> findByName(String username);
    */

    /**
     * Saves a new todo or updates an existing one.
     */
    Todo saveTodo(Todo todo);

    /**
     * Updates an existing todo by its ID.
     */
    Todo updateTodo(Long id, Todo todo);

    /**
     * Deletes a todo by its ID.
     **/
    void deleteTodoById(Long id);
}