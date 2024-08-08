package com.gestion.demo.services;

import com.gestion.demo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    Page<Todo> getTodosByTitleAndUsername(String title, String username, Pageable pageable);

    Page<Todo> getTodos(Pageable pageable); // New method to get all TODOs
    Todo saveTodo(Todo todo);
    void deleteTodoById(Long id);
    Todo getTodoById(Long id);
}