package com.crediya.auth.config;

import com.crediya.auth.model.jwt.JwtProvider;

import com.crediya.auth.model.jwt.PasswordHashProvider;
import com.crediya.auth.model.user.gateways.UserRepository;
import com.crediya.auth.usecase.auth.AuthUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public AuthUseCase authUseCase(UserRepository userRepository,
                                   PasswordHashProvider passwordHashProvider,
                                   JwtProvider jwtProvider) {
        return new AuthUseCase(userRepository, passwordHashProvider, jwtProvider);
    }
}

