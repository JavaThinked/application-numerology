package com.javathinked.application.numerology.controller.exception;

import com.javathinked.application.numerology.controller.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> handleResourceNotFoundException(NotFoundException exception) {
        return handleHttpException(HttpStatus.NOT_FOUND, exception);
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
