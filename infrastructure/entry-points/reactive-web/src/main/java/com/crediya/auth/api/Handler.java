package com.crediya.auth.api;

import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.api.dto.UserResponseDTO;
import com.crediya.auth.api.mapper.UserMapper;
import com.crediya.auth.model.user.User;
import com.crediya.auth.usecase.user.UserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Handler {
    private final UserUseCase userUseCase;
    private final Validator validator;

    public Mono<ServerResponse> getUserByEmail(ServerRequest serverRequest) {
        // useCase.logic();
        String email = serverRequest.queryParam("email")
                .orElseThrow(() -> new IllegalArgumentException("Email is required"));
        System.out.println("email = " + email);
        return userUseCase.findByEmail(email)
                .map(UserMapper::DomainToRespons)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        // useCase.logic();

        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(dto -> {
                    // 🔍 validar
                    Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(dto);
                    if (!violations.isEmpty()) {

                        String errors = violations.stream()
                                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                                .collect(Collectors.joining(", "));
                        return ServerResponse.badRequest().bodyValue(errors);
                    }

                    return userUseCase.saveUser(UserMapper.requestToDomain(dto))
                            // ✅ Entidad → DTO
                            .map(UserMapper::DomainToRespons)
                            .flatMap(saved -> ServerResponse.ok().bodyValue(saved));
                });
    }
}
