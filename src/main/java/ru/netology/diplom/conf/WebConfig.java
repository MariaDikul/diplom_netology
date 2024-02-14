package ru.netology.diplom.conf;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.diplom.jwt.JwtTokenFilter;

@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {
    private final JwtTokenFilter jwtTokenFilter;

    public WebConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*");
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and()
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .addFilterBefore(
                                        jwtTokenFilter,
                                        UsernamePasswordAuthenticationFilter.class))
                .build();
    }
}