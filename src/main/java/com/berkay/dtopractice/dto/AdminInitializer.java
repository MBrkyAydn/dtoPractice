package com.berkay.dtopractice.dto;

import com.berkay.dtopractice.entity.User;
import com.berkay.dtopractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${ADMIN_USERNAME:}") String adminUsername,
            @Value("${ADMIN_PASSWORD:}") String adminPassword
    ) {
        return args -> {
            if (adminUsername.isBlank() || adminPassword.isBlank()) {
                return;
            }

            if (userRepository.findByUsername(adminUsername).isPresent()) {
                return;
            }

            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");

            userRepository.save(admin);
        };
    }
}