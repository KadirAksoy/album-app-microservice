package com.kadiraksoy.noteservice.exception;

public class NoteCategoryNotFoundException extends RuntimeException{

    public NoteCategoryNotFoundException(String message) {
        super(message);
    }
}
