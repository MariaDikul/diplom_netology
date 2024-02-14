package ru.netology.diplom.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.netology.diplom.repository.SecurityRepository;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final SecurityRepository securityRepository;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {
        final String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null && securityRepository.validateToken(token)) {
            final JwtAuthentication jwtInfoToken = new JwtAuthentication();
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        fc.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("auth-token");
    }


}

