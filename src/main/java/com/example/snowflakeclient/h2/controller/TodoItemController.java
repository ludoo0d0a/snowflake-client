package com.example.snowflakeclient.h2.controller;

import com.example.snowflakeclient.h2.entity.TodoItem;
import com.example.snowflakeclient.h2.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService service;

    @GetMapping
    public List<TodoItem> list() {
        return service.list();
    }

    @PostMapping
    public TodoItem create(@RequestBody CreateTodoRequest request) {
        return service.create(request.title());
    }

    public record CreateTodoRequest(String title) {
    }
}

