package com.volomak.movieland.controller.exception;

import com.volomak.movieland.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    private JsonConverter jsonConverter;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(Exception e) {
        return jsonConverter.toJson(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IncorrectCredentials.class)
    public String incorrectCredentialsHandler(Exception e) {
        return jsonConverter.toJson(e.getMessage());
    }
}
