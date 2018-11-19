package com.andrei.backend.services.impl;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.model.Comment;
import com.andrei.backend.repositories.CommentRepository;
import com.andrei.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDTO> findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO dto = new CommentDTO();
            dto.setId(comment.getId());
            dto.setText(comment.getText());
            commentDTOs.add(dto);
        }
        return commentDTOs;
    }

    @Override
    public List<CommentDTO> findByAlertId(Long alertId) {
        List<Comment> comments = commentRepository.findByAlertId(alertId);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO dto = new CommentDTO();
            dto.setId(comment.getId());
            dto.setText(comment.getText());
            commentDTOs.add(dto);
        }
        return commentDTOs;
    }
}
