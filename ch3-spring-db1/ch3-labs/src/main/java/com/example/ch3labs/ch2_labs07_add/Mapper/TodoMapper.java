package com.example.ch3labs.ch2_labs07_add.Mapper;


import java.util.Optional;

public interface TodoMapper {

    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    void delete(Long id);

}


//@Repository
//public class TodoRepository {
//
//    private final Map<Long, com.example.ch2labs.labs07.domain.Todo> store = new HashMap<>();
//    private Long sequence = 0L;
//
//    public com.example.ch2labs.labs07.domain.Todo save(com.example.ch2labs.labs07.domain.Todo todo){
//        todo.setId(++sequence);
//        store.put(todo.getId(), todo);
//        return todo;
//    }
//
//    public Optional<com.example.ch2labs.labs07.domain.Todo> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    public void delete(Long id) {
//        store.remove(id);
//    }
//}