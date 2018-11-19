package com.andrei.backend.services;

import com.andrei.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class RestUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public RestUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.andrei.backend.model.User user = userRepository.findByEmail(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getValue());
        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }
}
