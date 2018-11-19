package com.andrei.backend;

import com.andrei.backend.model.Role;
import com.andrei.backend.model.User;
import com.andrei.backend.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BackendApplication.class, args);
		init(run);
	}

	private static void init(ConfigurableApplicationContext run) {
        UserRepository userRepository = run.getBeanFactory().getBean(UserRepository.class);
        PasswordEncoder passwordEncoder = run.getBeanFactory().getBean(PasswordEncoder.class);
        if (userRepository.findByEmail("admin")==null){
            User user = new User();
            user.setEmail("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }

    }
}
