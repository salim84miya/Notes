package com.example.notesapp.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> resourceNotFoundHandle(IllegalArgumentException exception){

        Map<String,String> errors = new HashMap<>();
        errors.put("errorMsg",exception.getMessage());

        return ResponseHandler.responseBuilder(null,
                HttpStatus.NOT_FOUND,errors,LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> invalidArgumentException(MethodArgumentNotValidException exception){

        Map<String,String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(),fieldError.getDefaultMessage()));



        return  ResponseHandler.responseBuilder(null,
                HttpStatus.BAD_REQUEST,errors,LocalDateTime.now());
    }
}
