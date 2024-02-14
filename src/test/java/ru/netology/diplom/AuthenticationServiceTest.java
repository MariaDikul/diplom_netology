package ru.netology.diplom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import ru.netology.diplom.repository.SecurityRepository;
import ru.netology.diplom.repository.UserRepository;
import ru.netology.diplom.service.AuthenticationService;
import ru.netology.diplom.service.MyUserDetailsService;
import ru.netology.diplom.service.TokenService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.diplom.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private SecurityRepository securityRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private MyUserDetailsService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Test
    void login() {
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(tokenService.generateToken()).thenReturn(TOKEN_1);
        assertEquals(SECURITY_RESPONSE, authenticationService.login(SECURITY_REQUEST));
        Mockito.verify(securityRepository, Mockito.times(1)).putTokenAndUsername(TOKEN_1, USERNAME_1);
    }

    @Test
    void logout() {
        authenticationService.logout(TOKEN_1);
        Mockito.verify(securityRepository, Mockito.times(1)).removeTokenAndUsernameByToken(TOKEN_1);
    }
}
