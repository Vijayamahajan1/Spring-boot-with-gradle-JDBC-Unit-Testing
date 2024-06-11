package com.Bnt.EmployeeManagementUsingJpa.Exception;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String msg, Integer id){
        super("User not Found with id:"+id);
    }
}
