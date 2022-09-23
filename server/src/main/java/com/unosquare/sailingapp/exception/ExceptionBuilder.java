package com.unosquare.sailingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ExceptionBuilder {
    public static ResponseEntity<ErrorResponse> buildErrorResponseRepresentation(
            final HttpStatus httpStatus, final String message) {
        return ResponseEntity.status(httpStatus)
                .body(ErrorResponse.builder()
                        .status(httpStatus.value())
                        .path("Not specified")
                        .error(httpStatus.getReasonPhrase())
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
