package com.example.ch4codeyourself.v2.service;

import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.PostPageResponse;
import com.example.ch4codeyourself.v2.dto.PostSearchRequset;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import com.example.ch4codeyourself.v2.dto.PostCreateRequest;
import com.example.ch4codeyourself.v2.dto.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


// 통합테스트
// 서비스 + 레포지토리 : 현재 코드 테스트 범위
// 단위테스트랑 다른것은 가정하지 않고, 실제 데이터를 줘서 테스트를 진행
// 실제데이터를 직접 주는 것 보다, 테스트용으로 h2 인메모리 DB 많이 사용 ( 가볍고 빠름, 테스트용)
@SpringBootTest // ??
@Transactional
@Disabled // qurydsl 사용하기 위해서 해놓음
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test // 왜 사용???
    void 게시글_생성_조회_성공(){
        //given
        //사용자가 requset 보냈다고 가정함
        PostCreateRequest postCreateRequest = new PostCreateRequest("테스트 제목","테스트 내용");

        //when
        PostResponse saved = postService.createPost(postCreateRequest);
        PostResponse found = postService.getPostById(saved.getId());

        //then
        assertThat(found.getTitle()).isEqualTo("테스트 제목");
        assertThat(found.getBody()).isEqualTo("테스트 내용");

    }


    @Test // 테스트 여러개 있을때 독립성 보장
    void 게시글_검색_페이징() {
        // 가데이터 만든 뒤 keyword 포함 조회
            
        //given
        for (int i = 1; i<=20 ; i ++){
            postRepository.save(new Post(null, "게시글"+i, "내용"));
        }
        for (int i = 1; i<=20 ; i ++){
            postRepository.save(new Post(null, "hello", "내용"));
        }
        PostSearchRequset requset = new PostSearchRequset("게시글", 0, 10);

        //when
        PostPageResponse response = postService.getAllPosts(requset);

        // then
        assertThat(response.getPage()).isEqualTo(0);
        assertThat(response.getSize()).isEqualTo(10);
        assertThat(response.getTotalCount()).isEqualTo(20);
        assertThat(response.getPosts()).hasSize(10);

    }
}