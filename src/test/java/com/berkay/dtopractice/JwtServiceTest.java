package com.berkay.dtopractice;
import com.berkay.dtopractice.security.JwtService;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
public class JwtServiceTest {
    @Test
    void tokenOlusturmaTesti() {
        JwtService jwtService = new JwtService();

        String secret = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";
        ReflectionTestUtils.setField(jwtService, "secret", secret);

        String token = jwtService.generateToken("berkay");

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void tokenIcindekiKullaniciAdiniCikarmaTesti() {
        JwtService jwtService = new JwtService();

        String secret = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";
        ReflectionTestUtils.setField(jwtService, "secret", secret);

        String token = jwtService.generateToken("berkay");

        String username = jwtService.extractUsername(token);

        assertEquals("berkay", username);
    }

    @Test
    void gecersizTokenReddedilmeli() {
        JwtService jwtService = new JwtService();

        String secret = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";
        ReflectionTestUtils.setField(jwtService, "secret", secret);

        assertThrows(
                Exception.class,
                () -> jwtService.extractUsername("gecersiz-token")
        );
    }

//    @Test
//    void tokenYoksaIstekDevamEtmeli() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        FilterChain filterChain = new MockFilterChain();
//
//        jwtAuthenticationFilter.doFilter(request, response, filterChain);
//
//        assertEquals(200, response.getStatus());
//    }

}
