package com.andrei.backend.services;

import com.andrei.backend.dto.AnnouncementDTO;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementDTO> findAll();
    void save(AnnouncementDTO dto);
}
