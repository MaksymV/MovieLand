package com.volomak.movieland.controller.exception;

public class RestrictAccessException extends RuntimeException {
    public RestrictAccessException() {
    }

    public RestrictAccessException(String message) {
        super(message);
    }
}
