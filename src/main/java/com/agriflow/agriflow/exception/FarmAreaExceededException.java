package com.agriflow.agriflow.exception;

public class FarmAreaExceededException extends RuntimeException {

    public FarmAreaExceededException(String message) {
        super(message);
    }
}