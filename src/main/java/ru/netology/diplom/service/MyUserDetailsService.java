package ru.netology.diplom.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.netology.diplom.domain.User;
import ru.netology.diplom.exception.UnauthorizedException;
import ru.netology.diplom.repository.UserRepository;

@Service
@AllArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UnauthorizedException("MyUserDetailsService Unauthorized");
        }
        return user;
    }
}
