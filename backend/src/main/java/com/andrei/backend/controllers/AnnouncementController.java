package com.andrei.backend.controllers;


import com.andrei.backend.dto.AnnouncementDTO;
import com.andrei.backend.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnnouncementController {
    private  final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;

    }

    @RequestMapping(value = "/free/announcement/list", method = RequestMethod.GET)
    public List<AnnouncementDTO> finalAll(){
        return announcementService.findAll();
    }

    @RequestMapping(value = "/auth/announcement", method = RequestMethod.POST)
    public void save(@RequestBody AnnouncementDTO dto){
        announcementService.save(dto);
    }
}
