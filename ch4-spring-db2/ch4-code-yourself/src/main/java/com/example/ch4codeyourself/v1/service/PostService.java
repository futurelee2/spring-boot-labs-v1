package com.example.ch4codeyourself.v1.service;


import com.example.ch4codeyourself.v1.domain.Post;
import com.example.ch4codeyourself.v1.dto.PostCreateRequest;
import com.example.ch4codeyourself.v1.dto.PostResponse;
import com.example.ch4codeyourself.v1.dto.PostUpdateRequest;
import com.example.ch4codeyourself.v1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//@Service
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
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostResponse.from(post))
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostResponse::from)// (post -> PostResponse.from(post) 랑 동일
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

    @Transactional // 스프링 트렌젝션으로 선언
    // 하나의 트렌젝션으로 선언해주면 아래 내용 커밋됨..?
    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        // 더티 체킹 -> 따로 저장 안해도 됨 (수정하면 쿼리가 나가는..?)
        // 트렉젝션이 끝나면
        // 변경 감지가 일어났을때 dirty checking -> SQL (업데이트 쿼리 날려줌)
        // 하지만 트렌젝션을 안 일어나면 (@Transactional 없으면) 업데이트 안됨

        
        // Mybatis 코드 (삭제코드)
//        int updated = postRepository.update(post);
//        if (updated == 0) {
//            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
//        }

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
//        if (deleted == 0) {
//            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
//        }
    }

    }