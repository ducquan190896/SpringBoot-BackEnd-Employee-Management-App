package com.apimanagement.employeemanagement;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apimanagement.employeemanagement.Exception.EntityNotFoundException;
import com.apimanagement.employeemanagement.Exception.ErrorResponse;

@ControllerAdvice
public class ExceptionhandlingApplication {
    

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException  ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), ex);

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), LocalDateTime.now(), ex);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
