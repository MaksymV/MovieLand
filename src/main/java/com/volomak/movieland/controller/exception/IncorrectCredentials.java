package com.volomak.movieland.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect Login or Password")
public class IncorrectCredentials extends RuntimeException {
    public IncorrectCredentials() {
        super();
    }

    public IncorrectCredentials(Throwable cause) {
        super(cause);
    }
}
