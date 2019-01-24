package com.andrei.backend.controllers;

import com.andrei.backend.dto.UserDTO;
import com.andrei.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/auth/user", method = RequestMethod.POST)
    public UserDTO findUserByEmail(@RequestBody String email){
        return userService.findByEmail(email);
    }

    @RequestMapping(value = "/free/user/register", method = RequestMethod.POST)
    public void register(@RequestBody UserDTO dto){
        userService.save(dto);
    }
}
