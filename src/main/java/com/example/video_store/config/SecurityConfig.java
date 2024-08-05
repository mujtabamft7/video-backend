package com.example.video_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/customers/register", "/api/customers/login").permitAll()
                .requestMatchers("/api/customers/**").permitAll()
                .requestMatchers("/api/movies/**").permitAll()
                .requestMatchers("/api/tvshows/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
