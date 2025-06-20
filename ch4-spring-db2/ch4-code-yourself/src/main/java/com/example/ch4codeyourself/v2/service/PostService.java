package com.example.ch4codeyourself.v2.service;


import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.*;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    // mapper -> JPA repository
    private final PostRepository postRepository;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = request.toDomain();
        Post saved = postRepository.save(post); // 테스트 코드짤때 saved 로 안받아줘서 오류남.
        return PostResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public PostPageResponse getAllPosts(PostSearchRequset search) {
        // jpa 에서 제공해주기 때문에 page 관련 정보를 넘겨주면 알아서 처리..?
        // pageable을 만들기위해서 아래 메서드 사용해서 만든다는 것 알아두기 ( 페이지 요청 정보를 표현하는 객체)
        // PageRequest는 구현체 역할?
        // jpa는 추상화가 되어있어서 어떻게 사용하는지만 알고 있으면 됨
        //
        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());
        // 키워드가 제목에 포함된 게시글 가져오기  -> 레포지토리에 메서드 만들기
        // 이렇게 하면
        //List<Post> content = postRepository.findBytitleContaining(search.getKeyword());
        Page<PostResponse> page =  postRepository.findByTitleContaining(search.getKeyword(), pageable) // pagealbe 같이 넣으면 Page 객체 나옴
                .map(post -> PostResponse.from(post)); // 리턴타입이 Page 임으로 stream의 map이랑 다름

        return PostPageResponse.from(page.getContent(), search, page.getTotalElements());
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostResponse::from)// (post -> PostResponse.from(post) 랑 동일
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

    @Transactional // 스프링 트렌젝션으로 선언
    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
//        if (deleted == 0) {
//            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
//        }
    }

    }