package com.berkay.dtopractice.config;

import com.berkay.dtopractice.entity.User;
import com.berkay.dtopractice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("berkay").isEmpty()) {

            User user = new User();

            user.setUsername("berkay");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole("USER");
            userRepository.save(user);
        }
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            userRepository.save(admin);
        }
    }
}