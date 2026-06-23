package com.haedong.backend.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 로그인은 누구나 접근 가능
                .requestMatchers("/api/auth/login", "/api/auth/encode", "/error").permitAll()
                // ADMIN만 접근 가능
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                // 기사 관리는 ADMIN, MANAGER만 접근 가능
                .requestMatchers("/api/drivers/**").hasAnyRole("ADMIN", "MANAGER")
                // 차량 관리는 ADMIN, MANAGER만 접근 가능
                .requestMatchers("/api/vehicles/**").hasAnyRole("ADMIN", "MANAGER")
                // 노선 관리는 ADMIN, MANAGER만 접근 가능
                .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "MANAGER")
                // 배차 관리는 ADMIN, MANAGER만 접근 가능
                .requestMatchers("/api/dispatches/**").hasAnyRole("ADMIN", "MANAGER")
                // 전속 배정 관리는 ADMIN, MANAGER만 접근 가능
                .requestMatchers("/api/assignments/**").hasAnyRole("ADMIN", "MANAGER")
                // 나머지는 로그인한 사용자만
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
