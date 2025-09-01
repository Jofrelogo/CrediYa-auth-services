package com.crediya.auth.api;

import com.crediya.auth.api.dto.UserRequestDTO;
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


    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
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

                    // ✅ mapear DTO → User
                    User user = UserMapper.toDomain(dto);

                    return userUseCase.saveUser(user)
                            .flatMap(saved -> ServerResponse.ok().bodyValue(saved));
                });
    }
}
