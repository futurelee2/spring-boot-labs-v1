package com.example.ch2labs.labs07.service;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public TodoResponse getTodo(Long id) {
        Todo todo = repository.findById(id).orElseThrow(()-> new RuntimeException("Todo not found"));
        return todoResponse(todo);
    }

    public TodoResponse todoResponse(Todo todo){
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
    }

    public TodoResponse createTodo(TodoCreateRequest request) {
        Todo todo = new Todo(null, request.getTitle(), request.getCompleted());
        Todo saveTodo = repository.save(todo);
        return todoResponse(saveTodo);
    }

    public void deleteTodo(Long id) {
        repository.delete(id);
    }
}
