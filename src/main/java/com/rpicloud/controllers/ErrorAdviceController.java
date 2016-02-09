package com.rpicloud.controllers;

import com.rpicloud.exceptions.MongoConnectionException;
import com.rpicloud.exceptions.ResourceNotFoundException;
import com.rpicloud.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by mixmox on 09/02/16.
 */

@ControllerAdvice
class ErrorAdviceController {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getStackTrace().toString(), HttpStatus.PRECONDITION_FAILED.value());

        return new ResponseEntity<>(errorMessage, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(value = MongoConnectionException.class)
    public ResponseEntity<ErrorMessage> mongoConnectionException(Exception exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getStackTrace().toString(), HttpStatus.SERVICE_UNAVAILABLE.value());

        return new ResponseEntity<>(errorMessage, HttpStatus.SERVICE_UNAVAILABLE);
    }
}