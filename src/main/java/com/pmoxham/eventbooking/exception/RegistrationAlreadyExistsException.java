package com.pmoxham.eventbooking.exception;

public class RegistrationAlreadyExistsException extends RuntimeException {
    public RegistrationAlreadyExistsException(String message) {
        super(message);
    }
}
