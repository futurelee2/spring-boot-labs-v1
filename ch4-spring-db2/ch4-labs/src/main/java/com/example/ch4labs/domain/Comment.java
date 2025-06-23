package com.example.ch4labs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    // private Long reviewId; => 아래 JoinColumn으로 매핑해줘서 안넣어줘도 됨
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private Comment parentComment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id" ) // Comment 테이블 안에 review_id 라는 컬럼 생성하고 이 컬럼은 Review의 컬럼을 참조하게 됨.
    private Review review;

}
