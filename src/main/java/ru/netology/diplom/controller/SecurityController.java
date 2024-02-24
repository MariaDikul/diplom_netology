package ru.netology.diplom.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.diplom.domain.SecurityRequest;
import ru.netology.diplom.domain.SecurityResponse;
import ru.netology.diplom.service.AuthenticationService;

@RestController
@AllArgsConstructor
public class SecurityController {

    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public SecurityResponse login(@RequestBody SecurityRequest securityRequest) {
        return authenticationService.login(securityRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
