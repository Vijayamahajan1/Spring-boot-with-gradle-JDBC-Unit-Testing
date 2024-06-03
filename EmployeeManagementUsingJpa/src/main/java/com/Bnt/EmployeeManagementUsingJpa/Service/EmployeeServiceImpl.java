package com.Bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();  
    }

    @Override
    public Optional<Employee> getEmployeeId(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee newEmployee) {
         return employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
    
}
