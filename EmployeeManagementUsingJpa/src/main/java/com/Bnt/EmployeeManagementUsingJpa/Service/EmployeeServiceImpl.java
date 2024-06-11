package com.Bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bnt.EmployeeManagementUsingJpa.Controller.EmployeeController;
import com.Bnt.EmployeeManagementUsingJpa.Exception.InvalidDataException;
import com.Bnt.EmployeeManagementUsingJpa.Exception.UserNotFoundException;
import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee employee2=null;
    try{
        if(employee.getName()==null || employee.getName().isEmpty() || employee.getSalary()<=0 ){
            throw new InvalidDataException("Invalid data provided Please insert valid data");
        }
        else{
            employee2= employeeRepository.save(employee);
            return employee2;
        }
    }catch(InvalidDataException e){
            logger.error("InvalidDataException", e);
    }
       return employee2;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();  
    }

    @Override
    public Optional<Employee> getEmployeeId(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        try{
            if(!optionalEmployee.isPresent()){
                  optionalEmployee=null;
                throw new UserNotFoundException("User not Found with id", id);
            }
        
        }catch(UserNotFoundException e){
             logger.error("UserNotfoundException",e);
        }
        return optionalEmployee;
    }

    @Override
    public Employee updateEmployee(Employee newEmployee) {
        Employee employee = null;
        try{
          Optional<Employee> optionalEmployee =  employeeRepository.findById(newEmployee.getId());
            if(optionalEmployee.isPresent()){
              employee =   employeeRepository.save(newEmployee);
             
            }
            else{
                employee=null;
                throw new  UserNotFoundException("User not found with id", newEmployee.getId());
            }
        }catch(UserNotFoundException e){
            logger.error("UserNotFoundException", e);
        }
        return employee;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try{
            if(!employeeRepository.existsById(id))
              throw new UserNotFoundException("UserNotFound with this id", id);
              else{
                employeeRepository.deleteById(id);
                return true;
              }
        }catch(UserNotFoundException e){
               logger.error("UserNotFoundException", e);
        }
        return false;
    }

    @Override
    public Employee updateEmployee2(Employee newEmployee) {
     Optional<Employee> optionalEmployee =  employeeRepository.findById(newEmployee.getId()); 
     if(optionalEmployee.isPresent()){
        return employeeRepository.save(newEmployee);
     } else{
       throw new UserNotFoundException("User Not Found With id: " ,newEmployee.getId());
     }
        
    }
    
}
