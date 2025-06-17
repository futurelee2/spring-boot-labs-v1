package com.example.ch4codeyourself.v2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
