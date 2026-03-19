package com.example.snowflakeclient.h2.service;

import com.example.snowflakeclient.h2.entity.TodoItem;
import com.example.snowflakeclient.h2.repository.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemRepository repository;

    public List<TodoItem> list() {
        return repository.findAll();
    }

    public TodoItem create(String title) {
        return repository.save(new TodoItem(title));
    }
}

