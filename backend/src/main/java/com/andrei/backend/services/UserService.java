package com.andrei.backend.services;

import com.andrei.backend.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findByEmail(String email);
    void save(UserDTO dto);
    void createClerk(UserDTO dto);

    List<UserDTO> findAll();

    void deleteAll(List<Long> ids);
}
