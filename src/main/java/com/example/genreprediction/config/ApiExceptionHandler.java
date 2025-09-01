package com.example.genreprediction.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleResponseStatus(ResponseStatusException ex) {
        return Map.of(
                "status", ex.getStatusCode().toString(),
                "error", ex.getReason()
        );
    }

    public Map<String, String> handleGeneric(Exception ex){
        return Map.of(
                "status", "500",
                "error", ex.getMessage()
        );
    }

}
