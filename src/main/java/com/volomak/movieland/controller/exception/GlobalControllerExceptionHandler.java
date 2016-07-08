package com.volomak.movieland.controller.exception;

import com.volomak.movieland.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    private JsonConverter jsonConverter;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> defaultErrorHandler(Exception e) {
        return new ResponseEntity<>(jsonConverter.toException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectCredentials.class)
    public ResponseEntity<String> incorrectCredentialsHandler(Exception e) {
        return new ResponseEntity<>(jsonConverter.toException(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
