package com.andrei.backend.repositories;

import com.andrei.backend.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert,Long> {
}
