package com.example.ch4labs.repository.Comment;

import com.example.ch4labs.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByReviewId(Integer reviewId, Pageable pageable);

    Page<Comment> findByReviewIdAndParentCommentIsNull(Integer reviewId, Pageable pageable);

    List<Comment> findByParentComment(Integer id);
}
