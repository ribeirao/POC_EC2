package com.ribeirao.cloud.application.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Errors> handleException(NotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Errors(new Object[] {e.getMessage()}));
    }

}
