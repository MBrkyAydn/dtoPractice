package com.berkay.dtopractice.controller;

import com.berkay.dtopractice.dto.RegisterRequest;
import com.berkay.dtopractice.entity.User;
import com.berkay.dtopractice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

        authService.register(registerRequest);
        return ResponseEntity.ok("Kullanıcı oluşturuldu.");

    }

}
