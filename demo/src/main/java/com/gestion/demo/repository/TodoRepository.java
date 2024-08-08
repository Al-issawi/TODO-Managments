package com.gestion.demo.repository;

import com.gestion.demo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Method to find todos by title (case-insensitive)
    @Query("SELECT t FROM Todo t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Todo> findByTitle(@Param("title") String title, Pageable pageable);

    // Method to find todos by username
    @Query("SELECT t FROM Todo t JOIN t.user u WHERE u.username = :username")
    Page<Todo> findByUsername(@Param("username") String username, Pageable pageable);

    Page<Todo> findByTitleContainingAndUser_Username(String title, String username, Pageable pageable);
}
