package com.example.NoteApplication.exception;

public class NoteDtoHasIdException extends BadRequestException{

    public NoteDtoHasIdException(String message) {
        super(message);
    }
}
