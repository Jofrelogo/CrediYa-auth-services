package com.crediya.auth.model.user.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailAlreadyExistsExceptionTest {

    @Test
    void shouldCreateExceptionWithEmailMessage() {
        EmailAlreadyExistsException ex = new EmailAlreadyExistsException("dup@domain.com");
        assertTrue(ex.getMessage().contains("dup@domain.com"));
    }
}

