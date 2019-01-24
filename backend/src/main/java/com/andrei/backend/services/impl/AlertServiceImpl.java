package com.andrei.backend.services.impl;

import com.andrei.backend.dto.AlertDTO;
import com.andrei.backend.model.Alert;
import com.andrei.backend.repositories.AlertRepository;
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

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public List<AlertDTO> findAll() {
        List<Alert> alerts = alertRepository.findAll();
        List<AlertDTO> alertDTOS = new ArrayList<>();
        for (Alert alert : alerts) {
            AlertDTO dto = new AlertDTO();
            dto.setId(alert.getId());
            dto.setText(alert.getText());
            dto.setTitle(alert.getTitle());
            dto.setDate(alert.getDate());
            dto.setUsername(alert.getUser().getName());
            alertDTOS.add(dto);
        }

        return alertDTOS.stream()
                .sorted(Comparator.comparing(AlertDTO::getDate).reversed())
                .collect(Collectors.toList());
    }
}
