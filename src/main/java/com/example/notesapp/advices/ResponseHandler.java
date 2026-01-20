package com.example.notesapp.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(
            Object data, HttpStatus status, Map<String,String> error, LocalDateTime timeStamp
            ){

        Map<String,Object> response = new HashMap<>();

        response.put("data",data);
        response.put("HttpStatus",status);
        response.put("Error",error);
        response.put("timestamp",timeStamp);

        return new ResponseEntity<>(response,status);
    }
}
