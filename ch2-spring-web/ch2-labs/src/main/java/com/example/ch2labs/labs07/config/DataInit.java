package com.example.ch2labs.labs07.config;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final TodoRepository repository;
    
    
    @PostConstruct // 생성 이후 바로 실행
    public void init(){
        repository.save(new Todo(null,"스프링부트 공부하기", false ));
        repository.save(new Todo(null,"리액트 수업 듣기", true ));

    }
}
