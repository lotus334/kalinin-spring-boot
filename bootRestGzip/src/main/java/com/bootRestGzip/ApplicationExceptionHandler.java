package com.bootRestGzip;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(com.bootRestGzip.ItemNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemNotFoundHandler(com.bootRestGzip.ItemNotFoundException ex) {
        return ex.getMessage();
    }
}