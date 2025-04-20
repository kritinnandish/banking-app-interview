package com.ecom.cbdg.interview.application.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
public class MockAuthController {

    private static SecretKey generateKey() {
        byte[] keyBytes = new byte[32]; // 256 bits (32 bytes)
        java.security.SecureRandom secureRandom = new java.security.SecureRandom();
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static final SecretKey SECRET_KEY = generateKey();
    private static final String BASE64_SECRET = Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());

    @PostMapping("/auth/token")
    public String getAuthToken() {
        return Jwts.builder()
                .setSubject("user")
                .claim("roles", List.of("new_app_role"))
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String getBase64Secret() {
        return BASE64_SECRET;
    }
}