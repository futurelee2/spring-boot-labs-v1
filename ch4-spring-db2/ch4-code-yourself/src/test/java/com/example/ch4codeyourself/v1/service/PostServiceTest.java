package com.example.ch4codeyourself.v1.service;

import com.example.ch4codeyourself.v1.domain.Post;
import com.example.ch4codeyourself.v1.dto.PostCreateRequest;
import com.example.ch4codeyourself.v1.dto.PostResponse;
import com.example.ch4codeyourself.v1.dto.PostUpdateRequest;
import com.example.ch4codeyourself.v1.repository.PostRepository;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// junit 도 테스트할때 어노테이션 봄

// junit : 테스트 코드를 실행시키는 주체 - 전체 흐름 담당
// Mockito : Mocking 기능 담당  - junit이 목킹 기능을 위해 모키토 사용

@Disabled // qurydsl 사용하기 위해서 해놓음
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    // 테스트를 위해 필요한 객체
    // 1. PostService
    // 2. PostRepository
    // 이 두 개 사용하기 위해 불러오기 위해 new 로 생성자 불러와도됨 (


    @Mock // 가짜 객체로 생성함 (현재는 테스트 대상이 아님)
    private PostRepository postRepository; // db 당장 잘되는지 안되는지 필요없음. 잘되는지 가정하고 서비스를 테스트함

    @InjectMocks // 스프링이 아닌 환경에서 의존성 주입하는 방법(의존성 주입해줘라 레포지토리를..)
    private PostService postService;


    @Test
    void 게시글_생성_성공() {
        // PostCreateRequest 가 필요함.
        // postRepository가 정상 실행된다는 걸 가정 => mock 객체
        // postResponse 리턴값을 검증
    
        // 일반적 패턴        
        // Given : 조건
        PostCreateRequest request = new PostCreateRequest("테스트 게시글", "내용");
        // Service 파일 내 postRepository.save(post); 와 동일하게 리턴값을 지정해줌 (데이터가 잘 올거라고 가정하고)
        given(postRepository.save(any(Post.class)))
                        .willReturn(new Post(1L, "테스트 게시글", "내용"));


        // When : 테스트 대상(=목적)
        PostResponse result = postService.createPost(request);

        // Then : 검증
        // 보통 검증할때 assertj 사용
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("테스트 게시글");
        assertThat(result.getBody()).isEqualTo("내용");
    }

    @Test
    void 게시글_수정_성공(){
        // given
        // 수정 내용, 기존 내용 정의
        Long id = 1L;
        PostUpdateRequest request = new PostUpdateRequest("수정 제목", "수정 내용");
        Post post = new Post(id, "기존 제목", "기존 내용");
        
        // 무엇을 리턴할지 지정해줌
        given(postRepository.findById(id)).willReturn(Optional.of(post));

        //when
        PostResponse result = postService.updatePost(id, request);

        // then (결과 검증)
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("수정 제목");
        assertThat(result.getBody()).isEqualTo("수정 내용");

        // 행동 검증
        verify(postRepository).findById(id);


    }
}