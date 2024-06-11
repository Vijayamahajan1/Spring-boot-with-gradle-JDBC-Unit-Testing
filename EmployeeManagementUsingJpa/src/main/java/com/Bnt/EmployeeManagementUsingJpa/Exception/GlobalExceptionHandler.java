package com.Bnt.EmployeeManagementUsingJpa.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException e){
    return new ResponseEntity<>("User Not Found with id",HttpStatus.NOT_FOUND);      
    }
}
