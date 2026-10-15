package com.berkay.dtopractice;

import com.berkay.dtopractice.dto.LoginRequest;
import com.berkay.dtopractice.dto.RegisterRequest;
import com.berkay.dtopractice.entity.User;
import com.berkay.dtopractice.repository.UserRepository;
import com.berkay.dtopractice.security.JwtService;
import com.berkay.dtopractice.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {


    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtService jwtService;
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    AuthService authService;


    @Test
    void registerTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("berkay");
        registerRequest.setPassword("1234");

        when(passwordEncoder.encode("1234")).thenReturn("hashedPassword");
        authService.register(registerRequest);
        verify(passwordEncoder).encode("1234");
        verify(userRepository).save(any(User.class));

    }

    @Test
    void registerTesti2() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("berkay");
        request.setPassword("1234");

        when(passwordEncoder.encode("1234"))
                .thenReturn("hashedPassword");

        authService.register(request);

        ArgumentCaptor<User> captor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();

        assertEquals("berkay", savedUser.getUsername());
        assertEquals("hashedPassword", savedUser.getPassword());
        assertEquals("USER", savedUser.getRole());
    }
    @Test
    void loginTesti() {
        LoginRequest request = new LoginRequest();
        request.setUsername("berkay");
        request.setPassword("1234");


        when(jwtService.generateToken("berkay"))
                .thenReturn("fake-jwt-token");

        String token = authService.login(request);

        assertEquals("fake-jwt-token", token);

        verify(authenticationManager).authenticate(
                any(UsernamePasswordAuthenticationToken.class)
        );

        verify(jwtService).generateToken("berkay");
    }

    @Test
    void loginBasarisizTesti() {
        LoginRequest request = new LoginRequest();
        request.setUsername("berkay");
        request.setPassword("yanlisSifre");

        when(authenticationManager.authenticate(
                any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Hatalı kullanıcı adı veya şifre"));

        assertThrows(BadCredentialsException.class, () ->
                authService.login(request));

        verify(jwtService, never()).generateToken("berkay");
    }

}
