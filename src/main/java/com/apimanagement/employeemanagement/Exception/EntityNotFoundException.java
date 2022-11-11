package com.apimanagement.employeemanagement.Exception;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}
