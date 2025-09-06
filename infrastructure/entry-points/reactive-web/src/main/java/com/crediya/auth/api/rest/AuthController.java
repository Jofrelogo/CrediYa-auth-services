package com.crediya.auth.api.rest;

import com.crediya.auth.api.dto.LoginRequest;
import com.crediya.auth.usecase.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody Mono<LoginRequest> authRequestMono) {
        return authRequestMono
                .flatMap(authRequest -> {
                    log.info("🔐 Login attempt for email={}", authRequest.getEmail());
                    return authUseCase.login(authRequest.getEmail(), authRequest.getPassword());
                })
                .map(token -> ResponseEntity.ok(new AuthResponse(token)))
                .doOnError(ex -> log.error("❌ Login failed", ex));
    }

    public record AuthRequest(String email, String password) {}
    public record AuthResponse(String token) {}
}

