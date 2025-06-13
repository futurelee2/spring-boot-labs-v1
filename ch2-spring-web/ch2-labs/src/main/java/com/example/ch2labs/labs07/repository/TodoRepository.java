package com.example.ch2labs.labs07.repository;

import com.example.ch2labs.labs07.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TodoRepository {

    private final Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 0L;

    public Todo save(Todo todo){
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
