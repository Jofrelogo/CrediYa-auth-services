package com.crediya.auth.api;

import com.crediya.auth.api.dto.ErrorResponse;
import com.crediya.auth.api.dto.TokenResponse;
import com.crediya.auth.usecase.auth.AuthUseCase;
import com.crediya.auth.api.dto.LoginRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LoginHandler {

    private final AuthUseCase authUseCase;

    public LoginHandler(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class)
                .flatMap(loginRequest ->
                        authUseCase.login(loginRequest.getEmail(), loginRequest.getPassword()))
                .flatMap(token ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new TokenResponse(token))
                )
                .onErrorResume(e ->
                        ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new ErrorResponse(e.getMessage()))
                );
    }
}


