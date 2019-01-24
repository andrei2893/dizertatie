package com.andrei.backend.services.impl;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.dto.NewCommentDTO;
import com.andrei.backend.model.Alert;
import com.andrei.backend.model.Comment;
import com.andrei.backend.model.User;
import com.andrei.backend.repositories.AlertRepository;
import com.andrei.backend.repositories.CommentRepository;
import com.andrei.backend.repositories.UserRepository;
import com.andrei.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              AlertRepository alertRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
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

    @Override
    public CommentDTO submitComment(NewCommentDTO dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Alert> alertOptional = alertRepository.findById(dto.getAlertId());
        if (userOptional.isPresent()&& alertOptional.isPresent()){
            Comment comment = new Comment();
            comment.setAlert(alertOptional.get());
            comment.setUser(userOptional.get());
            comment.setText(dto.getText());
            Comment saved = commentRepository.save(comment);
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(saved.getId());
            commentDTO.setText(comment.getText());
            return  commentDTO;
        }

        return null;
    }
}
