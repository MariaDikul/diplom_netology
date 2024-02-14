//package ru.netology.diplom.conf;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import ru.netology.diplom.jwt.JwtTokenFilter;
//import ru.netology.diplom.repository.UserRepository;
//
//import java.sql.SQLException;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final UserRepository userRepository;
//    private final JwtTokenFilter jwtTokenFilter;
//
//    public SecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
//        this.userRepository = userRepository;
//        this.jwtTokenFilter = jwtTokenFilter;
//    }
//
//    @Bean
//    public DaoAuthenticationConfigurer authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//
//        return auth.userDetailsService(userRepository::findByUsername);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @SuppressWarnings("removal")
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                )
//                .and()
//                .authorizeHttpRequests(
//                        authz -> authz
//                                .requestMatchers("/api/login").permitAll()
//                                .anyRequest().authenticated()
//                                .and()
//                                .addFilterBefore(
//                                        jwtTokenFilter,
//                                        UsernamePasswordAuthenticationFilter.class))
//                .build();
//
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}
