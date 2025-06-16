package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/rest/v2")
@RestController
public class RestController02 {
    // ResponseEntity = 스프링의 http 응답 객체
    // http : 헤더 + 바디  구조로 되어있음 (+ ResponseEntity 사용 시 상태코드 지정가능)

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello"); // 바디 이용 시, ok
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPosts(@PathVariable Long postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다");
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> posts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫번재 게시글", "내용1"));
        posts.add(new Post("두번재 게시글", "내용2"));
        posts.add(new Post("세번재 게시글", "내용3"));
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    //HTTP 상태 코드
    // 2XX : 성공
    // 3XX : 리다이렉트, 진행중
    // 4XX : 에러 ( 서버입장: 너 실수) - 명확히 지적
    // 5XX : 에러 ( 서버입장: 내 실수) - 퉁

    @PostMapping("/201")
    public ResponseEntity<String> get201() {
        return ResponseEntity.status(HttpStatus.CREATED).body("201"); // created :  이넘으로 되어있음. 생성이 성공했다.
        //http 의 상태코드를 추가할수있음
    }

    @DeleteMapping("/204")
    public ResponseEntity<String> delete204() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204"); // 잘 지워졌다.
    }

// http 명세에 따라서 정확히 응답을 해주는게 좋음. 이 코드는 200 ok 라 뜸 (올바르지 못함)
//    @DeleteMapping("/204")
//    public ResponseEntity<String> delete204() {
//        return ResponseEntity.ok("204");
//    }

    @GetMapping("401")
    // 로그인이 안되었을 때
    public ResponseEntity<String> get401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
    }

    @GetMapping("403")
    // 인가되지 않았을 때, 권한 없음
    public ResponseEntity<String> get403() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403");
    }

    @GetMapping("500")
    //서버에러 - 굳이 클라이언트가 알 필요없음
    public ResponseEntity<String> get500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
    }
    
    @GetMapping("/plan-text")
    public ResponseEntity<String> plainText(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN) // 어떤 타입으로 던져줄지 정해줌
                .body("hello");
    }
    
    @PostMapping("/location")
    public ResponseEntity<String> location(){
        // 리다이렉트 + location 
        // 헤더만 잘 지켜도 http 다른 페이지로 이동시켜준다?
        URI location = URI.create("/rest/v2/posts"); // 이쪽으로 이동 시켜줘  - 로케이션 헤더를 건드려서 stats를 300번대로 줌. 
        return ResponseEntity.status(HttpStatus.FOUND).location(location).build(); // 상태코드만 보고 리다이렉트 시켜줌
        
    }

//    @GetMapping("/no-cache")
//    public ResponseEntity<String> noCache() {

    }

