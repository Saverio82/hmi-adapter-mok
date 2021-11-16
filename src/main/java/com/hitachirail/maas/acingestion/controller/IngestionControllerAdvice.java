package com.hitachirail.maas.acingestion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class IngestionControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> httpMessageNotReadableExceptionHandler(Exception ex) {
        log.error("HttpMessageNotReadableException error", ex);

        return new ResponseEntity<>("Received a malformed object", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> genericExceptionHandler(Exception ex) {
        log.error("Generic error", ex);

        return new ResponseEntity<>("Generic Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
