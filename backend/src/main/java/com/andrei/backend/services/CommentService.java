package com.andrei.backend.services;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.dto.NewCommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    List<CommentDTO> findByAlertId(Long alertId);
    CommentDTO submitComment(NewCommentDTO dto);
}
