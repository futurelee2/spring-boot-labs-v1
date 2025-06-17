package com.example.ch4codeyourself.v1.repository;

import com.example.ch4codeyourself.v1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //crud
    // 메서드들을 자동 추상화함. 아무것도 안만들어도 crud가 만들어짐
    // Post 를 읽어서 자동으로 만들어줌
}
