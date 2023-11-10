package com.example.NoteApplication.exception;

public class NoteNotFoundException extends NotFoundException{

    public NoteNotFoundException(String message) {
        super(message);
    }
}
