package com.andrei.backend.services.impl;

import com.andrei.backend.dto.AnnouncementDTO;
import com.andrei.backend.model.Announcement;
import com.andrei.backend.repositories.AnnouncementRepository;
import com.andrei.backend.repositories.UserRepository;
import com.andrei.backend.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, UserRepository userRepository) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public List<AnnouncementDTO> findAll() {
        List<Announcement> alerts = announcementRepository.findAll();
        List<AnnouncementDTO> alertDTOS = new ArrayList<>();
        for (Announcement alert : alerts) {
            AnnouncementDTO dto = new AnnouncementDTO();
            dto.setId(alert.getId());
            dto.setText(alert.getText());
            dto.setDate(alert.getDate());
            dto.setUserId(alert.getUser().getId());
            alertDTOS.add(dto);
        }

        return alertDTOS.stream()
                .sorted(Comparator.comparing(AnnouncementDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void save(AnnouncementDTO dto) {
        Announcement alert = new Announcement();
        alert.setDate(dto.getDate());
        alert.setText(dto.getText());
        userRepository.findById(dto.getUserId()).ifPresent(u -> {
            alert.setUser(u);
            announcementRepository.save(alert);
        });
    }
}
