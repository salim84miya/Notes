package com.example.notesapp.util.advices;

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

    //No resource found exception handling

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> resourceNotFoundHandle(IllegalArgumentException exception){

        Map<String,String> errors = new HashMap<>();
        errors.put("errorMsg",exception.getMessage());

        return ResponseHandler.responseBuilder(null,
                HttpStatus.NOT_FOUND,errors,LocalDateTime.now());
    }


    //Invalid method argument exception

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

    //Internal server errors

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError(Exception exception){

        Map<String,String> error= new HashMap<>();

        error.put("errorMsg",exception.getLocalizedMessage());

        return ResponseHandler.responseBuilder(null,HttpStatus.BAD_GATEWAY,error,LocalDateTime.now());
    }
}
