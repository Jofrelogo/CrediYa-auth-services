package com.crediya.auth.api.exception;

import com.crediya.auth.api.dto.UserRequestDTO;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException {

    private final Set<ConstraintViolation<UserRequestDTO>> violations;

    public ValidationException(Set<ConstraintViolation<UserRequestDTO>> violations) {
        super("Error de validación en los datos de entrada");
        this.violations = violations;
    }

    public Set<ConstraintViolation<UserRequestDTO>> getViolations() {
        return violations;
    }
}

