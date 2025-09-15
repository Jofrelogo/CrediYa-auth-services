package com.crediya.auth.api.exception;



import com.crediya.auth.model.common.ErrorResponse;
import com.crediya.auth.model.user.exceptions.EmailAlreadyExistsException;
import com.crediya.auth.model.user.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleUserNotFoundException() {
        UserNotFoundException ex = new UserNotFoundException("test@domain.com");

        Mono<ResponseEntity<ErrorResponse>> response = handler.handleDomainException(ex);

        StepVerifier.create(response)
                .assertNext(error -> {
                    assert error.getBody().getError().contains("test@domain.com");
                    assert error.getBody().getStatus() == 404;
                })
                .verifyComplete();
    }

    @Test
    void shouldHandleEmailAlreadyExistsException() {
        EmailAlreadyExistsException ex = new EmailAlreadyExistsException("dup@domain.com");

        Mono<ResponseEntity<ErrorResponse>> response = handler.handleDomainException(ex);

        StepVerifier.create(response)
                .assertNext(error -> {
                    assert error.getBody().getError().contains("dup@domain.com");
                    assert error.getBody().getStatus() == 409;
                })
                .verifyComplete();
    }

    /*@Test
    void shouldHandleGenericException() {
        Exception ex = new Exception("Generic error");

        Mono<ResponseEntity<ErrorResponse>> response = handler.handleGenericException(ex);

        StepVerifier.create(response)
                .assertNext(error -> {
                    assert error.getBody().getError().equals("Generic error");
                    assert error.getBody().getStatus() == 500;
                })
                .verifyComplete();
    }*/
}
