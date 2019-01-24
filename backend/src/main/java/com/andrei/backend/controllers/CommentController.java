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

    @RequestMapping(value = "/free/comment/list", method = RequestMethod.GET)
    public List<CommentDTO> findAll(){
        return commentService.findAll();
    }

    @RequestMapping(value = "/free/comment/alert/{alertId}", method = RequestMethod.GET)
    public List<CommentDTO> findByAlertId(@PathVariable Long alertId){
        return commentService.findByAlertId(alertId);
    }

    @RequestMapping(value = "/auth/comment", method = RequestMethod.POST)
    public CommentDTO submitComment(@RequestBody NewCommentDTO dto){
        return commentService.submitComment(dto);
    }
}
