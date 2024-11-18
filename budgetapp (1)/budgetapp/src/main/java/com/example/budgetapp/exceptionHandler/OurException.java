package com.example.budgetapp.exceptionHandler;

public class OurException extends RuntimeException {
    public OurException(String message) {
        super(message);
    }

    public OurException(String message, Throwable cause) {
        super(message, cause);
    }
}
