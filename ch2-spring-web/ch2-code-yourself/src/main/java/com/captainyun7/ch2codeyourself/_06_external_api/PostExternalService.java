package com.captainyun7.ch2codeyourself._06_external_api;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PostExternalService {

    private final WebClient webClient;

    public PostExternalService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    public Post[] getAllPost() {
        Post[] posts = webClient.get().uri("/posts")
                .retrieve()
                .bodyToMono(Post[].class)
                .block();
        return posts;
    }
}
