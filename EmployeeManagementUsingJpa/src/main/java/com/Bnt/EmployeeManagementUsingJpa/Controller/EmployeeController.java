package com.Bnt.EmployeeManagementUsingJpa.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bnt.EmployeeManagementUsingJpa.Exception.UserNotFoundException;
import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Service.EmployeeService;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee){
        logger.info(("The employee data is saved"));
        Employee newEmp = employeeService.saveEmployee(employee);
        if(newEmp==null){
            return new ResponseEntity<Object>("Invalid data please insert valid data.",HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<Object>(newEmp,HttpStatus.OK);
        }
       
    }
    
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeId(@PathVariable("id") int id){
        logger.info("Get the employee with id: {id}");
        try{
            Optional<Employee> optionalEmployee =  employeeService.getEmployeeId(id);
            if(optionalEmployee==null){
                return new ResponseEntity<Object>("User not found with id",HttpStatus.NOT_FOUND);
            
            }else{
                return new ResponseEntity<Object>(optionalEmployee, HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            logger.error("Exception", e);
        }
        return null;
      
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id")int id, @RequestBody Employee newEmployee){
        logger.info("update the user with given id");
        try{
            newEmployee.setId(id);
            Employee updatedEmployee = employeeService.updateEmployee(newEmployee);
            return ResponseEntity.ok(updatedEmployee);
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
       
        
    }

    @DeleteMapping("/{id}")
    public void  deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
    }
} 

