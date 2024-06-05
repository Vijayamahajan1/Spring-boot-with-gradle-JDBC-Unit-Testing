package com.Bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;

public interface EmployeeService {
  public Employee saveEmployee (Employee employee);

  public List<Employee> getAllEmployee();

  public Optional<Employee> getEmployeeId (int id);

  public Employee updateEmployee(Employee newEmployee);

  public boolean deleteEmployee(int id);
}
