package com.pmoxham.eventbooking.exception;

public class EventDateInPastException extends RuntimeException {
    public EventDateInPastException(String message) {
        super(message);
    }
}
