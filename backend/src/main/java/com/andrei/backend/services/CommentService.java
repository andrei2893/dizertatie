package com.andrei.backend.services;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.dto.NewCommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findByAlertId(Long alertId);
    CommentDTO submitAlertComment(NewCommentDTO dto);
    List<CommentDTO> findByAnnouncementId(Long announcementId);
    CommentDTO submitAnnouncementComment(NewCommentDTO dto);
}
