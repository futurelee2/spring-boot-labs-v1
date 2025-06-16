package com.captainyun7.ch2codeyourself._04_3tiers_crud.service;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostCreateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostUpdateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.exception.PostNotFoundException;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //  컨포넌트임  - div main 처럼 기능은 같지만 이름만 다른 느낌
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;


    //    public Post getPost(Long id){
//        return repository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
//        // findById = > 옵셔널로 넘어옴.
//        // value가 넘어오지 않으면 에러
//
//    }

    public PostResponse getPost(Long id){
        Post post = repository.findById(id).orElseThrow(()-> new PostNotFoundException(id));

        return toResponse(post);
    }



//    public List<Post> getAllPosts() {
//        return repository.findAll();
//    }

    public List<PostResponse> getAllPosts() {
        return repository.findAll().stream()
                .map(post ->toResponse(post))
                .collect(Collectors.toList());
    }

    
    // post 객체를 postResponse 객체로 변환
    public PostResponse toResponse(Post post){
        return new PostResponse(post.getId(),post.getTitle(),post.getBody());
    }


    ////////////////////////////////////////////////////// Create
    public PostResponse createPost(PostCreateRequest request) {
        // dto -> domain 반환
        // 로직 처리 -> 현재 없음
        Post post = new Post(null,request.getTitle(), request.getBody());
        Post savedPost = repository.save(post);
        return toResponse(savedPost);
    }
    ////////////////////////////////////////////////////// Delete
    public void deletePost(Long id) {
        repository.delete(id);
    }

    public PostResponse upDatePost(Long id, PostUpdateRequest request) {
        // 1. id 를 통해서 수정할 게시글을 가져온다
        // 2. 가져왔다면 (null이 아니면) request 의 값으로 수정한다.

        Post post = repository.findById(id).orElseThrow(() -> new RuntimeException("Post not found")); // Runtime을 상속받은게 PostNotFoundException
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        //repository.save(post); // 참조타입이라서 save 안해줘도 됨
        return toResponse(post);
    }
}
