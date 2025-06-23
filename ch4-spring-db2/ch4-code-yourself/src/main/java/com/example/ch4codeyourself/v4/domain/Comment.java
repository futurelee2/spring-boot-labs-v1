package com.example.ch4codeyourself.v4.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false) // null 이면 안된다. 
    private String content;

    @Column(nullable = false) // null 이면 안된다.
    private String author;

    @Column(nullable = false , updatable = false) // null 안됨. 수정 불가
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY) // 연결되어있으면 값이 들어오게됨
    @JoinColumn(name = "post_id") // 외래키 컬럼을 만듦( 외래키 : 다른테이블의 PK를 참조하는 컬럼)
    private Post post;


}
