package com.andrei.backend.services.impl;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.dto.NewCommentDTO;
import com.andrei.backend.model.Alert;
import com.andrei.backend.model.Announcement;
import com.andrei.backend.model.Comment;
import com.andrei.backend.model.User;
import com.andrei.backend.repositories.AlertRepository;
import com.andrei.backend.repositories.AnnouncementRepository;
import com.andrei.backend.repositories.UserRepository;
import com.andrei.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;

    @Autowired
    public CommentServiceImpl(AlertRepository alertRepository,
                              UserRepository userRepository,
                              AnnouncementRepository announcementRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<CommentDTO> findByAlertId(Long alertId) {
        Optional<Alert> optionalAlert = alertRepository.findById(alertId);
        if (optionalAlert.isPresent()){
            Alert alert = optionalAlert.get();
            List<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : alert.getComments()) {
                CommentDTO dto = new CommentDTO();
                dto.setId(comment.getId());
                dto.setText(comment.getText());
                commentDTOs.add(dto);
            }
            return commentDTOs;
        }
        return null;
    }

    @Override
    public CommentDTO submitAlertComment(NewCommentDTO dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Alert> alertOptional = alertRepository.findById(dto.getEntityId());
        if (userOptional.isPresent()&& alertOptional.isPresent()){
            Comment comment = new Comment();
            comment.setUser(userOptional.get());
            comment.setText(dto.getText());
            Alert alert = alertOptional.get();
            alert.getComments().add(comment);
            Alert newAlert = alertRepository.save(alert);
            Comment savedComment = newAlert.getComments().stream()
                    .filter(it -> it.getText().equals(comment.getText()))
                    .findFirst()
                    .get();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(savedComment.getId());
            commentDTO.setText(comment.getText());
            return  commentDTO;
        }

        return null;
    }

    @Override
    public List<CommentDTO> findByAnnouncementId(Long announcementId) {
        Optional<Announcement> optional = announcementRepository.findById(announcementId);
        if (optional.isPresent()){
            Announcement announcement = optional.get();
            List<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : announcement.getComments()) {
                CommentDTO dto = new CommentDTO();
                dto.setId(comment.getId());
                dto.setText(comment.getText());
                commentDTOs.add(dto);
            }
            return commentDTOs;
        }
        return null;
    }

    @Override
    public CommentDTO submitAnnouncementComment(NewCommentDTO dto) {
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        Optional<Announcement> announcementOptional = announcementRepository.findById(dto.getEntityId());
        if (userOptional.isPresent()&& announcementOptional.isPresent()){
            Comment comment = new Comment();
            comment.setUser(userOptional.get());
            comment.setText(dto.getText());
            Announcement announcement = announcementOptional.get();
            announcement.getComments().add(comment);
            Announcement announcement1 = announcementRepository.save(announcement);
            Comment savedComment = announcement1.getComments().stream()
                    .filter(it -> it.getText().equals(comment.getText()))
                    .findFirst()
                    .get();
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(savedComment.getId());
            commentDTO.setText(comment.getText());
            return  commentDTO;
        }

        return null;
    }
}
