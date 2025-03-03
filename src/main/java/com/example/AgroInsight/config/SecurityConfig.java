package com.example.AgroInsight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // Your HTML Origin
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Standardize your API paths and add all public endpoints
                        .requestMatchers(
                                "/users/signup",
                                "/users/login",
                                "/users/details",
                                "/users/recommendation",
                                "/users/recommendation/history",
                                // Add any other public endpoints here
                                "/error"
                        ).permitAll()
                        // For debugging during development, you might temporarily allow all requests
                        // .anyRequest().permitAll()
                        .anyRequest().permitAll()
                )
                // Make sure you have an authentication provider configured
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}