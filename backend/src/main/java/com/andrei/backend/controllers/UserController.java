package com.andrei.backend.controllers;

import com.andrei.backend.dto.UserDTO;
import com.andrei.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/auth/user/all", method = RequestMethod.GET)
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/auth/user/all/delete", method = RequestMethod.DELETE)
    public void deleteAllUsers(@RequestParam List<Long> ids){
        userService.deleteAll(ids);
    }

    @RequestMapping(value = "/auth/user/admin", method = RequestMethod.POST)
    public void createClerk(@RequestBody UserDTO dto){
        userService.createClerk(dto);
    }

    @RequestMapping(value = "/free/user/register", method = RequestMethod.POST)
    public void register(@RequestBody UserDTO dto){
        userService.save(dto);
    }
}
