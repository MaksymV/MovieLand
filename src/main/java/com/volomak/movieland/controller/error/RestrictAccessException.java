package com.volomak.movieland.controller.error;

public class RestrictAccessException extends RuntimeException {
    public RestrictAccessException() {
    }

    public RestrictAccessException(String message) {
        super(message);
    }
}
