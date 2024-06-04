package com.Bnt.EmployeeManagementUsingJpa.Exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg){
        super(msg);
    }    
}
