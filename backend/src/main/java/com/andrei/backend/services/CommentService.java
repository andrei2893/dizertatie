package com.andrei.backend.services;

import com.andrei.backend.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    List<CommentDTO> findByAlertId(Long alertId);
}
