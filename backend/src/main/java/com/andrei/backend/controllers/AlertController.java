package com.andrei.backend.controllers;


import com.andrei.backend.dto.AlertDTO;
import com.andrei.backend.model.Alert;
import com.andrei.backend.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class AlertController {
    private  final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<AlertDTO> finalAll(){
        return alertService.findAll();
    }
}
