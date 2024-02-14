package ru.netology.diplom.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.diplom.domain.SecurityRequest;
import ru.netology.diplom.domain.SecurityResponse;
import ru.netology.diplom.domain.User;
import ru.netology.diplom.exception.UnauthorizedException;
import ru.netology.diplom.repository.SecurityRepository;
import ru.netology.diplom.repository.UserRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {

    private UserRepository userRepository;
    private SecurityRepository securityRepository;
    private TokenService tokenService;

    public SecurityResponse login(SecurityRequest securityRequest) {
        final String username = securityRequest.getLogin();
        final String password = securityRequest.getPassword();
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UnauthorizedException("AuthenticationService 1 Unauthorized error");
        } else if (password.equals(user.getPassword())) {
            final String token = tokenService.generateToken();
            securityRepository.putTokenAndUsername(token, username);
            return new SecurityResponse(token);
        } else throw new UnauthorizedException("AuthenticationService 2 Unauthorized error");

    }

    public void logout(String authToken) {
        securityRepository.removeTokenAndUsernameByToken(authToken);
    }

}
