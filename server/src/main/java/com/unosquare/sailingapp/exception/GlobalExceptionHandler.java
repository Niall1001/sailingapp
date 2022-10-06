package com.unosquare.sailingapp.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({
            NoContentException.class})
    public ResponseEntity<ErrorResponse> handleNoContentException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.NO_CONTENT, exception.getMessage());
    }

    @ExceptionHandler({
            BadRequestException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class, InternalServerErrorException.class, EmptyResultDataAccessException.class,
            IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleInternalServerError(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler({
            ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> handleExpiredJTWException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler({
            UnauthorizedException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.FORBIDDEN, exception.getMessage());
    }

}
