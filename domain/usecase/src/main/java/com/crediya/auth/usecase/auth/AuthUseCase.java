package com.crediya.auth.usecase.auth;

import com.crediya.auth.model.jwt.JwtProvider;

import com.crediya.auth.model.jwt.PasswordHashProvider;
import com.crediya.auth.model.user.User;
import com.crediya.auth.model.user.gateways.UserRepository;
import reactor.core.publisher.Mono;

public class AuthUseCase {

    private final UserRepository userRepository;
    private final PasswordHashProvider passwordHashProvider;
    private final JwtProvider jwtProvider;

    public AuthUseCase(UserRepository userRepository,
                       PasswordHashProvider passwordHashProvider,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordHashProvider = passwordHashProvider;
        this.jwtProvider = jwtProvider;
    }

    public Mono<String> login(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .flatMap(user -> validatePassword(user, rawPassword));
    }

    private Mono<String> validatePassword(User user, String rawPassword) {
        if (Boolean.FALSE.equals(user.getEnabled())) {
            return Mono.error(new RuntimeException("Usuario deshabilitado"));
        }

        if (!passwordHashProvider.matches(rawPassword, user.getPasswordHash())) {
            return Mono.error(new RuntimeException("Credenciales inválidas"));
        }

        String token = jwtProvider.generateToken(user.getDni(), user.getEmail(), user.getRole());
        return Mono.just(token);
    }
}


