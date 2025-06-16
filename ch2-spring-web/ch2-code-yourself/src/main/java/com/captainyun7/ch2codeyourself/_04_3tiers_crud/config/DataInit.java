package com.captainyun7.ch2codeyourself._04_3tiers_crud.config;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final PostRepository repository;

    // 생성자 -> 초기화 -> ... -> 소멸
    @PostConstruct
    public void init(){
        repository.save(new Post(null, "첫번째 게시글", "첫번째 게시글 내용"));
        repository.save(new Post(null, "두번째 게시글", "두번째 게시글 내용"));
        repository.save(new Post(null, "세번째 게시글", "세번째 게시글 내용"));

    }
}
