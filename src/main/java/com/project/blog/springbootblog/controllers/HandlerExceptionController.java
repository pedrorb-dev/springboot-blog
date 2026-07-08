package com.project.blog.springbootblog.controllers;

import com.project.blog.springbootblog.exceptions.Error;
import com.project.blog.springbootblog.exceptions.NoEntityFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> handler404(NoHandlerFoundException e) {
        Error error=new Error(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now(), e.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> handlerResourceNoFound(NoResourceFoundException e) {
        Error error=new Error(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now(), e.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler(NoEntityFound.class)
    public ResponseEntity<?> handlerNoEntity(NoEntityFound e) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("date", LocalDate.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
