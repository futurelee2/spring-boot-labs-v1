package com.example.ch3labs.ch2_labs07_add.config;


import com.example.ch3labs.ch2_labs07_add.Mapper.TodoMapper;
import com.example.ch3labs.ch2_labs07_add.domain.Todo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final TodoMapper todoMapper;
    
    
    @PostConstruct // 생성 이후 바로 실행
    public void init(){
        todoMapper.save(new Todo(null,"스프링부트 공부하기", false ));
        todoMapper.save(new Todo(null,"리액트 수업 듣기", true ));

    }
}
