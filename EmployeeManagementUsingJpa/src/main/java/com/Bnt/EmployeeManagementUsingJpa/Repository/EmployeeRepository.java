package com.Bnt.EmployeeManagementUsingJpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{


}
