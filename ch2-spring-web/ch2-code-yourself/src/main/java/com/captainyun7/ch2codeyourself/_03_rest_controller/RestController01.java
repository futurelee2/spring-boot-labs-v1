package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v1") // 모든 HTTP 메서드를 받음 (get, post.. 등)
public class RestController01 {

    @GetMapping("hello")
    public String hello(){
        return "hello~~";
    }

    @GetMapping("/posts/{postId}")
    public Post post(@PathVariable int postId){
        Post  post = new Post("샘플 게시글", "샘플 내용입니다");
        post.setId(1L);
        return post; // 자동 json으로 변환
    }

    @GetMapping("/posts")
    public List<Post> posts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫번재 게시글", "내용1"));
        posts.add(new Post("두번재 게시글", "내용2"));
        posts.add(new Post("세번재 게시글", "내용3"));
        return posts;

    }
    //post 메서드: http body 안에 내용을 담아 요청
    // joson 형식으로 데이터 요청이 옴 {title : ... , body: ...}
    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        post.setId(1L);
        return post;
    }

}
