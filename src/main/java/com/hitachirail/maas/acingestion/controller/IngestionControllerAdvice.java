package com.hitachirail.maas.acingestion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class IngestionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> genericExceptionHandler(Exception ex) {
        log.error("Generic error", ex);

        return new ResponseEntity<>("Generic Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
