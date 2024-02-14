package ru.netology.diplom.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
@Slf4j
public class TokenService {
    private String token;

    public TokenService() {
    }

    public String generateToken() {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        token = new BigInteger(1, bytes).toString(16);
        return token;
    }
}