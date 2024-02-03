package com.example.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> HandleRuntimeExceptin(RuntimeException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException e){
        return new ResponseEntity<String>(e.getMsg(), HttpStatus.BAD_REQUEST);
    }
}
