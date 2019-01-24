package com.andrei.backend.services;

import com.andrei.backend.dto.UserDTO;

public interface UserService {
    UserDTO findByEmail(String email);
    void save(UserDTO dto);
}
