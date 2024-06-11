package com.Bnt.EmployeeManagementUsingJpa.Controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try{
        Employee newEmp = employeeService.saveEmployee(employee);
        if(newEmp==null){
            return new ResponseEntity<Object>("Invalid data please insert valid data.",HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<Object>(newEmp,HttpStatus.OK);
        }
    }catch(Exception e){
        logger.error("Exception",e);
    }
       return null;
    }
    
    @GetMapping
    public List<Employee> getAllEmployee(){
        logger.info("Got all the Employees details");
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
    public ResponseEntity<Object> updateEmployee(@PathVariable("id")int id, @RequestBody Employee newEmployee){
        logger.info("update the user with given id");
      try{
        Matcher nMatcher = nPattern.matcher(newEmployee.getName());
        if(!nMatcher.matches()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name format");
        }
          Employee employee = employeeService.updateEmployee(newEmployee);
          if(employee==null){
            return new ResponseEntity<Object>("User not found this id",HttpStatus.NOT_FOUND);
          }
          else{
            return new ResponseEntity<Object>(employee,HttpStatus.OK);
          }
      }catch(Exception e){
        logger.error("Exception", e);
      }
       return null ;

        
    }
   
    //Global Exception
    String Name_Regex = "^[a-zA-Z\\s]+$";
    Pattern nPattern = Pattern.compile(Name_Regex);
    @PutMapping("/update2/{id}")
    public ResponseEntity<Object> updateEmployee2(@PathVariable ("id")int id, @RequestBody Employee newEmployee) {
        try {
            Matcher nMatcher = nPattern.matcher(newEmployee.getName());
            if(!nMatcher.matches()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name format");
            }
            Employee updatedEmployee = employeeService.updateEmployee2(newEmployee);
            return ResponseEntity.ok(updatedEmployee);
            
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id "+ id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteEmployee(@PathVariable("id") int id){
           boolean result = employeeService.deleteEmployee(id);
        try{
           
            if(result==false){
                return new ResponseEntity<Object>("UserNotFound With id",HttpStatus.NOT_FOUND);
                
            }
            else{
                return new ResponseEntity<Object>("Employee deleted Successfully",HttpStatus.OK);
            }
        }catch(Exception e){
            logger.error("Exception", e);
        }
        return null;
    }


} 

