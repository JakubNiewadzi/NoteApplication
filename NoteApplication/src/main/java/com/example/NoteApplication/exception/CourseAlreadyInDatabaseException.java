package com.example.NoteApplication.exception;

public class CourseAlreadyInDatabaseException extends BadRequestException{
    public CourseAlreadyInDatabaseException(String message) {
        super(message);
    }
}
