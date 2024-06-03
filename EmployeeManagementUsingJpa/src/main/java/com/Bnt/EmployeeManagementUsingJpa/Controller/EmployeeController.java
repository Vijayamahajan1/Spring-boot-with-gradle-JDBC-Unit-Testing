package com.Bnt.EmployeeManagementUsingJpa.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Service.EmployeeService;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
       return employeeService.saveEmployee(employee);
    }
    
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeId(@PathVariable("id") int id){
        return employeeService.getEmployeeId(id);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee newEmployee){
        return employeeService.updateEmployee(newEmployee);
    }

    @DeleteMapping("/{id}")
    public void  deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
    }
} 

