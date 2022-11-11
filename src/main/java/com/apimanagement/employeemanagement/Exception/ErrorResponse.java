package com.apimanagement.employeemanagement.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private LocalDateTime localDateTime;
    private Throwable throwable;

    public ErrorResponse(String message, LocalDateTime localDateTime, Throwable throwable) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "error: " + this.message + " \n date of time: "+ this.localDateTime.toString();
    }
}
