package com.crediya.auth.model.user.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithEmailMessage() {
        UserNotFoundException ex = new UserNotFoundException("test@domain.com");
        assertTrue(ex.getMessage().contains("test@domain.com"));
    }
}

