package com.berkay.dtopractice.security;

import com.berkay.dtopractice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return User
//                .withUsername("berkay")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build();
        com.berkay.dtopractice.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı"));
        return  org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();




    }
}

