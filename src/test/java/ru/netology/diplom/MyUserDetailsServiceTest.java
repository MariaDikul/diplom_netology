package ru.netology.diplom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.netology.diplom.exception.UnauthorizedException;
import ru.netology.diplom.repository.UserRepository;
import ru.netology.diplom.service.MyUserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.netology.diplom.DataForTests.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MyUserDetailsServiceTest {

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findByUsername(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void loadUserByUsername() {
        assertEquals(USER_1, myUserDetailsService.loadUserByUsername(USERNAME_1));
    }

    @Test
    void loadUserByUsernameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> myUserDetailsService.loadUserByUsername(USERNAME_2));
    }
}