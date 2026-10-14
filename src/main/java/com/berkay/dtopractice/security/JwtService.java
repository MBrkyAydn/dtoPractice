package com.berkay.dtopractice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getsigningKey() {
        byte[] encodedKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encodedKey);


    }

    public String generateToken(String username) {
        Date now = new Date();
        Date exDate = new Date(now.getTime() + 1000 * 60 * 60);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(exDate)
                .signWith(getsigningKey())
                .compact();


    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();

    }
    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getsigningKey())
                .build()
                .parseClaimsJws(token);

    }

}
