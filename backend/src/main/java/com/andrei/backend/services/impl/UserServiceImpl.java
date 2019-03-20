package com.andrei.backend.services.impl;

import com.andrei.backend.dto.UserDTO;
import com.andrei.backend.model.Role;
import com.andrei.backend.model.User;
import com.andrei.backend.repositories.UserRepository;
import com.andrei.backend.services.UserService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapToUserDTO(user);
    }

    @Override
    public void save(UserDTO dto) {
        saveUser(dto, Role.BASIC);
    }

    @Override
    public void createClerk(UserDTO dto) {
        saveUser(dto, Role.CLERK);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll(List<Long> ids) {
        ids.forEach(userRepository::deleteById);
    }

    private void saveUser(UserDTO dto, Role role) {
        User existing = userRepository.findByEmail(dto.getEmail());
        if (existing !=null ){
            throw new IllegalArgumentException("User is not unique!");
        }
        User user = new User();
        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(role);
        userRepository.save(user);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setAddress(user.getAddress());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }
}
