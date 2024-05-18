package com.kadiraksoy.noteservice.exception;

public class NoteNotFoundException extends RuntimeException{

    public NoteNotFoundException(String message) {
        super(message);
    }
}
