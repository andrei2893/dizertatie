package com.andrei.backend.services.impl;

import com.andrei.backend.dto.AlertDTO;
import com.andrei.backend.model.Alert;
import com.andrei.backend.repositories.AlertRepository;
import com.andrei.backend.repositories.UserRepository;
import com.andrei.backend.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository, UserRepository userRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AlertDTO> findAll() {
        List<Alert> alerts = alertRepository.findAll();
        List<AlertDTO> alertDTOS = new ArrayList<>();
        for (Alert alert : alerts) {
            AlertDTO dto = new AlertDTO();
            dto.setId(alert.getId());
            dto.setAddress(alert.getAddress());
            dto.setText(alert.getText());
            dto.setTitle(alert.getTitle());
            dto.setDate(alert.getDate());
            dto.setLat(alert.getLat());
            dto.setLng(alert.getLng());
            dto.setUserId(alert.getUser().getId());
            alertDTOS.add(dto);
        }

        return alertDTOS.stream()
                .sorted(Comparator.comparing(AlertDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void save(AlertDTO dto) {
        Alert alert = new Alert();
        alert.setDate(dto.getDate());
        alert.setLat(dto.getLat());
        alert.setLng(dto.getLng());
        alert.setText(dto.getText());
        alert.setTitle(dto.getTitle());
        alert.setAddress((dto.getAddress()));
        userRepository.findById(dto.getUserId()).ifPresent(u -> {
            alert.setUser(u);
            alertRepository.save(alert);
        });

    }
}
