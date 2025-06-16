package com.captainyun7.ch2codeyourself._06_external_api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/external-api/posts")
@RequiredArgsConstructor
public class PostExternalController {
    private final PostExternalService postService;

    @GetMapping
    public Post[] post() {
        return postService.getAllPost();
    }
}
