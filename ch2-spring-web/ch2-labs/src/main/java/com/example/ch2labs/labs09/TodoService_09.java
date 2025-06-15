package com.example.ch2labs.labs09;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;


@Service
//@RequiredArgsConstructor
public class TodoService_09 {
    private final WebClient webClient;
    private final Map<Long, Todo> todoMap = new HashMap<>();

    public TodoService_09(){
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    public boolean fetchTodos(){
        Todo[] todoList  = webClient.get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(Todo[].class)
                .block(); // 동기방식

        if(todoList != null){
            for(Todo todo : todoList){
                todoMap.put(todo.getId(), todo);
            }
            return true;
        }
        return false;
    };

    public TodoDto getId(Long id) {
        if(todoMap.isEmpty()){ // 초기화해서 존재하지만 (not null) 비어있는지를 확인
            fetchTodos();
        }
        Todo todo = todoMap.get(id);

        if(todo == null) return null;

        return toDto(todo);
    };

    public TodoDto toDto(Todo todo){
        return new TodoDto(todo.getUserId(), todo.getId(), todo.getTitle(), todo.getCompleted());
    }


}
