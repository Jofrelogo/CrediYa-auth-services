package com.crediya.auth.api;

import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.api.mapper.UserMapper;
import com.crediya.auth.usecase.user.UserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import java.util.Set;

@Component
@RequiredArgsConstructor
public class Handler {

    private final UserUseCase userUseCase;
    private final Validator validator;
    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    public Mono<ServerResponse> getUserByEmail(ServerRequest serverRequest) {
        String email = serverRequest.queryParam("email")
                .orElseThrow(() -> new IllegalArgumentException("Email is required"));
        log.info("📩 Request received to search for user with email: {}", email);
        return userUseCase.findByEmail(email)
                .map(UserMapper::DomainToRespons)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(dto -> {
                    log.info("📩 Request received to create user: {}", dto.getEmail());
                    // ✅ Validación con Bean Validation
                    Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
                    if (!violations.isEmpty()) {
                        log.warn("⚠️ Validation violations in createUser: {}", violations);
                        throw new ConstraintViolationException(violations);
                    }

                    // ✅ Guardar usuario
                    return userUseCase.saveUser(UserMapper.requestToDomain(dto))
                            .doOnNext(user -> log.info("✅ Usuario guardado: {}", user.getEmail()))
                            .doOnError(err -> log.error("❌ Error guardando usuario {}", dto.getEmail(), err))
                            .map(UserMapper::DomainToRespons)
                            .flatMap(saved -> ServerResponse.ok().bodyValue(saved));
                });
    }
}
