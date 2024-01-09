package org.example.eventsmanagementsystem.converter.advice;

import org.example.eventsmanagementsystem.controller.util.ResponseBuilderHelper;
import org.example.eventsmanagementsystem.model.dto.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponsePayload> handleControllerExceptions(RuntimeException ex) {
        return ResponseBuilderHelper.buildResponsePayload(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
