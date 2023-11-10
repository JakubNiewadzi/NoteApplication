package com.example.NoteApplication.exception;

public class CourseDtoHasIdException extends BadRequestException{
    public CourseDtoHasIdException(String message) {
        super(message);
    }
}
