package com.andrei.backend.controllers;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.dto.NewCommentDTO;
import com.andrei.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/free/comment/alert/{alertId}", method = RequestMethod.GET)
    public List<CommentDTO> findByAlertId(@PathVariable Long alertId){
        return commentService.findByAlertId(alertId);
    }

    @RequestMapping(value = "/free/comment/announcement/{announcementId}", method = RequestMethod.GET)
    public List<CommentDTO> findByAnnouncementId(@PathVariable Long announcementId){
        return commentService.findByAnnouncementId(announcementId);
    }

    @RequestMapping(value = "/auth/comment/alert", method = RequestMethod.POST)
    public CommentDTO submitAlertComment(@RequestBody NewCommentDTO dto){
        return commentService.submitAlertComment(dto);
    }

    @RequestMapping(value = "/auth/comment/announcement", method = RequestMethod.POST)
    public CommentDTO submitAnnouncementComment(@RequestBody NewCommentDTO dto){
        return commentService.submitAnnouncementComment(dto);
    }
}
