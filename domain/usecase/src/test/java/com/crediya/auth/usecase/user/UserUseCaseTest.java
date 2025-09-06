package com.crediya.auth.usecase.user;

import com.crediya.auth.model.jwt.PasswordHashProvider;
import com.crediya.auth.model.user.User;
import com.crediya.auth.model.user.exceptions.EmailAlreadyExistsException;
import com.crediya.auth.model.user.exceptions.UserNotFoundException;
import com.crediya.auth.model.user.gateways.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class UserUseCaseTest {

    private UserRepository userRepository;
    private UserUseCase userUseCase;
    private PasswordHashProvider passwordHashProvider;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userUseCase = new UserUseCase(userRepository, passwordHashProvider);
    }

    @Test
    void shouldFindUserByEmail() {
        User user = User.builder().email("test@domain.com").firstName("John").build();
        when(userRepository.findByEmail("test@domain.com")).thenReturn(Mono.just(user));

        StepVerifier.create(userUseCase.findByEmail("test@domain.com"))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void shouldThrowErrorWhenUserNotFound() {
        when(userRepository.findByEmail("notfound@domain.com"))
                .thenReturn(Mono.empty());

        StepVerifier.create(userUseCase.findByEmail("notfound@domain.com"))
                .expectError(UserNotFoundException.class)
                .verify();
    }

    @Test
    void shouldSaveUserSuccessfully() {
        User user = User.builder().email("new@domain.com").firstName("Jane").build();
        when(userRepository.findByEmail("new@domain.com")).thenReturn(Mono.empty());
        when(userRepository.save(user)).thenReturn(Mono.just(user));

        StepVerifier.create(userUseCase.saveUser(user))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        User existing = User.builder()
                .dni("111222333")
                .firstName("Mario")
                .lastName("Rossi")
                .email("dup@domain.com")
                .build();

        when(userRepository.findByEmail("dup@domain.com")).thenReturn(Mono.just(existing));

        StepVerifier.create(userUseCase.saveUser(existing))
                .expectError(EmailAlreadyExistsException.class)
                .verify();

        verify(userRepository, times(1)).findByEmail("dup@domain.com");
        verify(userRepository, never()).save(any());
    }
}
