package com.kadiraksoy.notecategoryservice.exception;

public class NoteCategoryNotFoundException extends RuntimeException{

    public NoteCategoryNotFoundException(String message) {
        super(message);
    }
}
