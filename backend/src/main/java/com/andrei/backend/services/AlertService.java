package com.andrei.backend.services;

import com.andrei.backend.dto.AlertDTO;

import java.util.List;

public interface AlertService {
    List<AlertDTO> findAll();

    void save(AlertDTO dto);
}
