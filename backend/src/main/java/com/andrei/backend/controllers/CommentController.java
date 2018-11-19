package com.andrei.backend.controllers;

import com.andrei.backend.dto.CommentDTO;
import com.andrei.backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CommentDTO> findAll(){
        return commentService.findAll();
    }

    @RequestMapping(value = "/alert/{alertId}", method = RequestMethod.GET)
    public List<CommentDTO> findByAlertId(@PathVariable Long alertId){
        return commentService.findByAlertId(alertId);
    }
}
