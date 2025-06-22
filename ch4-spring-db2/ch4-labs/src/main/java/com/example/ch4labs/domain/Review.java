package com.example.ch4labs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String  author;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany (mappedBy = "review", fetch = FetchType.LAZY)
    private List<Comment> comments;
 }
