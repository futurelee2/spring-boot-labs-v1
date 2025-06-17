package com.example.ch3labs.ch2_labs07_add.service;


import com.example.ch3labs.ch2_labs07_add.domain.Todo;
import com.example.ch3labs.ch2_labs07_add.dto.TodoCreateRequest;
import com.example.ch3labs.ch2_labs07_add.dto.TodoResponse;
import com.example.ch3labs.ch2_labs07_add.dto.TodoUpdateRequest;
import com.example.ch3labs.ch2_labs07_add.exception.TodoNotFoundException;
import com.example.ch3labs.ch2_labs07_add.Mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoMapper todoMapper;

    public TodoResponse getTodo(Long id) {
        Todo todo = todoMapper.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
        return todoResponse(todo);
    }

    public TodoResponse todoResponse(Todo todo){
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getCompleted());
    }

    public TodoResponse createTodo(TodoCreateRequest request) {
        Todo todo = new Todo(null, request.getTitle(), request.getCompleted());
        Todo saveTodo = todoMapper.save(todo);
        return todoResponse(saveTodo);
    }

    public void deleteTodo(Long id) {
        todoMapper.delete(id);
    }

    public TodoResponse updateTodo(Long id, TodoUpdateRequest request) {
        Todo todo = todoMapper.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.getCompleted());
        return todoResponse(todo);

    }
}
