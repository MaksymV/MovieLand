package com.volomak.movieland.controller.error;

/**
 * Created by grey4 on 05.07.2016.
 */
public class RestrictAccessException extends RuntimeException {
    public RestrictAccessException() {
    }

    public RestrictAccessException(String message) {
        super(message);
    }
}
