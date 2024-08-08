package com.gestion.demo.services;

import com.gestion.demo.model.Todo;
import com.gestion.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Page<Todo> getTodosByTitleAndUsername(String title, String username, Pageable pageable) {
        return todoRepository.findByTitleContainingAndUser_Username(title, username, pageable);
    }

    @Override
    public Page<Todo> getTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }
}
