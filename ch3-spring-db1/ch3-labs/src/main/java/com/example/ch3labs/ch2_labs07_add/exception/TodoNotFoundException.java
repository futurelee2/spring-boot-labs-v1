package com.example.ch3labs.ch2_labs07_add.exception;


public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id){
        super("해당 Todo 리스트를 찾을 수 없습니다. id =" + id);
    }
}
