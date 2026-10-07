package com.berkay.dtopractice.service;

import com.berkay.dtopractice.dto.LoginRequest;
import com.berkay.dtopractice.dto.RegisterRequest;
import com.berkay.dtopractice.entity.User;
import com.berkay.dtopractice.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
    public void register(RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("USER");
userRepository
        .save(user);
    }
public boolean login(LoginRequest loginRequest) {
        User user =userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Kullanıcı Bulunamadı"));
        return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

}

}
