package com.example.NoteApplication.exception.handler;

import com.example.NoteApplication.exception.NoteDtoHasIdException;
import com.example.NoteApplication.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class NoteDtoHasIdHandler {

    @ExceptionHandler({NoteDtoHasIdException.class})
    public final ResponseEntity<String> handleNoteNotFoundException(NoteDtoHasIdException exception){
        String message = exception.getMessage();

        log.error("There has been an error trying to find a note in repository");

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
