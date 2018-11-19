package com.andrei.backend.repositories;

import com.andrei.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAlertId(Long alertId);
}
