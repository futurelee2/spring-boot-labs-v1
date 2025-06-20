package com.example.ch4codeyourself.v2.repository;

import com.example.ch4codeyourself.v2.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //crud
    // 메서드들을 자동 추상화함. 아무것도 안만들어도 crud가 만들어짐
    // Post 를 읽어서 자동으로 만들어줌
    
    // jpa 가 구현체를 알아서 만들어줌
    // post 를 찾을때 키워드.. 를 포함해서 찾아줌
    // 이걸 자동생성 메서드 라고 함. (= JPA 가 메서드명만 보고 자동으로 만들어줌)
    Page<Post> findByTitleContaining(String keyword, Pageable pageable); // 예약어

}
