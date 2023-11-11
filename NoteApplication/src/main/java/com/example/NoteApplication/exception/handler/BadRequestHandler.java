package com.example.NoteApplication.exception.handler;

import com.example.NoteApplication.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BadRequestHandler {

    @ExceptionHandler({BadRequestException.class})
    public final ResponseEntity<String> handleBadRequestException(BadRequestException exception){
        String message = exception.getMessage();

        log.error("Given request is invalid.");

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
