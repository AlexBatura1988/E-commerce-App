package com.ecommerce.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentMotValidException(MethodArgumentNotValidException e) {
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError)err).getField();
            String msg = err.getDefaultMessage();
            response.put(fieldName,msg);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e){
        String msg = e.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(APIExceptions.class)
    ResponseEntity<String> myAPIException(APIExceptions e){
        String msg = e.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}
