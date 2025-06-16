package com.captainyun7.ch2codeyourself._04_3tiers_crud.controller;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostCreateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostUpdateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.service.PostService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor // final 키워드 있으면 
public class PostController {
    
    private final PostService postService; // 무조건 초기화 해줘야하는데 -> RequiredArgsConstructor 필요함

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

//    @Autowired
//    PostService postService;

    // (1) 클라이언트 요청 : 게시글 하나 줘, id가 이거야(id 같이줌)
    // (2) 컨트롤러 -> 서비스한테 이거 찾아라
    // (3) 서비스 -> 레포지토리에게 위임
    // (4) 레포지토리 -> 서비스에게 게시글 데이터 전달
    // (5) 서비스 -> 컨트롤러에게 다시 찾은 게시글 반환( DTO )
    // R이 제일 중요..
    @GetMapping("/{id}")
    //단 건 조회
//    public Post getPost(@PathVariabl
//    e Long id){
//        return postService.getPost(id);
//    }

    // 위 코드 리팩토링
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

//    @GetMapping
//    // 전체조회
//    public List<Post> getAllPosts(){
//        return postService.getAllPosts();
//    }

    // 위 코드 리팩토링
    @GetMapping
    // 전체조회
    public ResponseEntity<List<PostResponse>>getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    ////////////////////////////////////////////////////// Create
    // POST 요청 (등록) : 클라이언트 -> 서버  (데이터 전송)
    // 클라이언트가 데이터를 JSON 형태로 넘겨줌
    // {title : 스트링부트 써볼수록 좋네요 -> dto의 title 필드 값으로 (값만 떼어서 객체화 시켜줘야함..?)
    //    body: "저만 그런가요"""  -> dto의 body 필드 값으로 매핑
    //    }

    // POST /posts
    // 리턴 : 등록한 객체를 반환 (DTO)
    // POST 로 준다는 것은 HTTP BODY 에 들어감
    @PostMapping // 위에 접두어 구현되어있음
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request){
        //request 안에는 값이 들어가있음 . 스트링부트 써볼수록 좋네요 , 저만 그런가요..
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(request));
    }

    /////////////////////////////////////////////////// Delete

    // DELTE /api/v1/posts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // noContent 도 status 코드임 (204)
    }

    /////////////////////////////////////////////////// Update
    // PUT /api/v1/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request){
        // 기능상 문제는 없지만 코딩컨벤션, 리펙토링 관련해서 다시 생성해줌 => PostUpdateRequest 생성
        return ResponseEntity.ok(postService.upDatePost(id,request));
    }

}
