package com.javathinked.application.numerology.controller.exception;

import com.javathinked.application.numerology.controller.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class NumerologyControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handleBadRequestException(BadRequestException exception) {
        return handleHttpException(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException exception) {
        return handleHttpException(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response> handleAuthenticationException(Exception exception) {
        var error = new Response(HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN .getReasonPhrase(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "An error occurs during the authentication process",
                "The Api Key or the Header Name is not correct");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    private ResponseEntity<Response> handleHttpException(HttpStatus httpStatus, Exception exception) {
        var error = new Response(httpStatus.value(),
                httpStatus.getReasonPhrase(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                exception.getMessage(),
                exception.getCause().getMessage());
        return ResponseEntity.status(httpStatus).body(error);
    }
}
