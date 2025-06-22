package com.example.ch4codeyourself.v4.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // JPA 에서는 도메인을 엔티티로 부름. DB의 테이블이랑 매핑됨 (DB에서 만든 posts랑)
@Table(name = "posts")
public class Post {
    @Id // jakarta의 id임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    // 추가
    private String author;
    @CreationTimestamp // db 에 알려줌
    private LocalDateTime createdAt;


    // Comment 엔티티에 post 가 연관관계 주인이라는 뜻
    @OneToMany(mappedBy = "post", fetch= FetchType.LAZY) // comment를 쓸때 가져옴
    private List<Comment> comments = new ArrayList<>();

}
