package ru.netology.diplom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.diplom.repository.SecurityRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.diplom.DataForTests.*;

public class SecurityRepositoryTest {

    private SecurityRepository securityRepository;
    private final Map<String, String> testTokensAndUsernames = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        securityRepository = new SecurityRepository();
        securityRepository.putTokenAndUsername(TOKEN_1, USERNAME_1);
        testTokensAndUsernames.clear();
        testTokensAndUsernames.put(TOKEN_1, USERNAME_1);
    }

    @Test
    void putTokenAndUsername() {
        String beforePut = securityRepository.getUsernameByToken(TOKEN_2);
        assertNull(beforePut);
        securityRepository.putTokenAndUsername(TOKEN_2, USERNAME_2);
        String afterPut = securityRepository.getUsernameByToken(TOKEN_2);
        assertEquals(USERNAME_2, afterPut);
    }

    @Test
    void removeTokenAndUsernameByToken() {
        String beforeRemove = securityRepository.getUsernameByToken(TOKEN_1);
        assertNotNull(beforeRemove);
        securityRepository.removeTokenAndUsernameByToken(TOKEN_1);
        String afterRemove = securityRepository.getUsernameByToken(TOKEN_1);
        assertNull(afterRemove);
    }

    @Test
    void getUsernameByToken() {
        assertEquals(testTokensAndUsernames.get(TOKEN_1), securityRepository.getUsernameByToken(TOKEN_1));
    }
}
