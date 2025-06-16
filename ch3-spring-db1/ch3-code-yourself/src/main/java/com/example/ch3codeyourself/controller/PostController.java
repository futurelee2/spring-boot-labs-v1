package com.example.ch3codeyourself.controller;

import com.example.ch3codeyourself.dto.PostCreateRequest;
import com.example.ch3codeyourself.dto.PostResponse;
import com.example.ch3codeyourself.dto.PostSearchRequest;
import com.example.ch3codeyourself.dto.UpdateRequest;
import com.example.ch3codeyourself.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody  PostCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(request));
    }
    //posts
    //posts/keword=스프링 
    // 모두 여기에서 처리함
//    @GetMapping
//    // 전체조회
//    public ResponseEntity<List<PostResponse>> getPosts(){
//        return ResponseEntity.ok(postService.getPosts());
//    }
//

    @GetMapping
    public ResponseEntity<List<PostResponse>> searchPosts(PostSearchRequest search){ // @ModelAttribute 이건 생략 가능함.
        return ResponseEntity.ok(postService.searchPosts(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpdateRequest response, @PathVariable Long id){
        return ResponseEntity.ok(postService.updatePost(response,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


}
