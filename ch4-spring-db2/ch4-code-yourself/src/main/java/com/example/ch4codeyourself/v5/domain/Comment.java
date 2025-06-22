package com.example.ch4codeyourself.v5.domain;

import jakarta.persistence.*;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id") // 생략하게 되면 내가 지정한 "변수명_id" 으로 컬럼 생성됨 , 즉, parent_id 로 자동 생성
    private Comment parent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id") // 이름을 정확히 명시하고 싶을때 적음. 생략해도 됨. 혹은 다른 이름으로 생성하고 싶을때 사용.
    // 보통 현업에서 사용해주는게 좋음
    private Post post;


}
