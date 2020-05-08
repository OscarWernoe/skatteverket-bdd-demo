package com.example.demo.controller;

import com.example.demo.domain.AuthenticationFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthenticationFailedException.class})
    public ResponseEntity<Object> handleAuthenticationFailedException(AuthenticationFailedException e, WebRequest wr) {
        return new ResponseEntity<>("Authentication failed", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
