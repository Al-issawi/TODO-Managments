package com.gestion.demo.services;

import com.gestion.demo.repository.TodoRepository;
import com.gestion.demo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    // Injecting TodoRepository dependency
    @Autowired
    private TodoRepository todoRepository;


    // Method to get a paginated list of todos
    @Override
    public Page<Todo> getTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    // Method to get a todo by its ID
    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    // Method to save a new todo
    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

   /* @Override
       public Todo updateTodo(Long id, Todo updatedTodo) {
     if (existingTodo.isPresent()) {
            Todo updatedTodo = existingTodo.get();
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setCompleted(todo.isCompleted());
            updatedTodo.setUser(todo.getUser());
            return todoRepository.save(updatedTodo);
        } else {
            throw new RuntimeException("Todo not found with id " + id);
        }
    }
*/
    // Method to update an existing todo by its ID
    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    // Setting updated values

                    todo.setTitle(updatedTodo.getTitle());
                    todo.setCompleted(updatedTodo.isCompleted());
                    todo.setUser(updatedTodo.getUser());

                    // Saving updated todo to the repository
                    return todoRepository.save(todo);
                }).orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }


    // Method to delete an existing todo by its ID
    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
