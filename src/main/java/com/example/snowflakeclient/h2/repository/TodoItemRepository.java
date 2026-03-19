package com.example.snowflakeclient.h2.repository;

import com.example.snowflakeclient.h2.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}

