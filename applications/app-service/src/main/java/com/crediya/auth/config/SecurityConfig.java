package com.crediya.auth.config;

import com.crediya.auth.model.jwt.JwtProvider;
import com.crediya.auth.r2dbc.security.JwtProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProvider jwtProvider(JwtProperties jwtProperties) {
        return new JwtProviderImpl(jwtProperties.getSecret(), jwtProperties.getExpiration());
    }
}




