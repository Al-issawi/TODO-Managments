package com.gestion.demo.repository;

import com.gestion.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Repository interface for Todo entities.
 * Extends JpaRepository and PagingAndSortingRepository to provide CRUD operations
 * and pagination and sorting capabilities.
 */

public interface TodoRepository extends JpaRepository<Todo, Long>, PagingAndSortingRepository<Todo, Long> {
}
