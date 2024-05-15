package com.kadiraksoy.userservice.exception;

public class EmailAlreadyExisting extends RuntimeException {
    public EmailAlreadyExisting(String message) {
        super(message);
    }
}
