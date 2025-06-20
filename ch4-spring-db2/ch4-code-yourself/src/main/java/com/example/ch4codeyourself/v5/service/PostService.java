package com.example.ch4codeyourself.v5.service;


import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.post.*;
import com.example.ch4codeyourself.v5.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

//    @Transactional(readOnly = true)
//    public PostPageResponse getAllPosts(PostSearchRequset search) {
//        // jpa 에서 제공해주기 때문에 page 관련 정보를 넘겨주면 알아서 처리..?
//        // pageable을 만들기위해서 아래 메서드 사용해서 만든다는 것 알아두기 ( 페이지 요청 정보를 표현하는 객체)
//        // PageRequest는 구현체 역할?
//        // jpa는 추상화가 되어있어서 어떻게 사용하는지만 알고 있으면 됨
//
//
//        // 만약 author가 포함되어있으면 => findByAuthor
//        // keyword 가 포함되어있으면 => findByTitleContaining
//        // 조건 분기
//        Page<Post> posts = null;
//        Pageable pageable = PageRequest.of(search.getPage(), search.getSize());
//        if(search.getKeyword()!=null && search.getAuthor() !=null) {
//            //posts = postRepository.findByTitleContainingAndAuthor(search.getKeyword(), search.getAuthor(), pageable);
//            posts = postRepository.searchByTitleContainingAndAuthor(search.getKeyword(), search.getAuthor(), pageable);
//
//        }else if(search.getKeyword() != null){
//            posts = postRepository.findByTitleContaining(search.getKeyword(), pageable);
//        } else if(search.getAuthor() != null){
//            posts = postRepository.findByAuthor(search.getAuthor(), pageable);
//        } else if(search.getCreatedAt() != null){
//            // posts = postRepository.findByCreatedAtAfter(search.getCreatedAt(), pageable); // 메서드 자동화로 만듦
//            //posts = postRepository.searchByAuthorAndTitle(search.getCreatedAt(), pageable);
//            posts = postRepository.searchByCreatedAtWithQueryDSL(search.getCreatedAt(), pageable);
//        }else{
//            posts = postRepository.findAll(pageable);
//        }
//
//         Page<PostResponse> page = posts.map(post -> PostResponse.from(post));
//
//        return PostPageResponse.from(page.getContent(), search, page.getTotalElements());
//    }

    @Transactional(readOnly = true)
    public PostPageResponse getAllPosts(PostSearchRequset request) {
        // 모든 조회를 이 메서드 하나로 하기
        Page<PostResponse> page =  postRepository.search(request);
        return PostPageResponse.from(page.getContent(), request, page.getTotalElements());
    }   


//    @Transactional(readOnly = true)
//    public PostResponse getPostById(Long id) {
//        return postRepository.findById(id)
//                .map(PostResponse::from)// (post -> PostResponse.from(post) 랑 동일
//                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
//    }
    @Transactional(readOnly = true)
    public PostWithCommentsResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostWithCommentsResponse::from)// (post -> PostResponse.from(post) 랑 동일
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