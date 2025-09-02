package com.crediya.auth.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp; // Momento del error
    private int status;              // Código HTTP
    private String error;
    private List<ErrorDetail> errors; // Lista de errores
}

