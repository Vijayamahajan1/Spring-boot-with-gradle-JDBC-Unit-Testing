package com.Bnt.EmployeeManagementUsingJpa.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Repository.EmployeeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Test
    void saveEmployeeTest(){
        Employee ExpectedResult = new Employee(1,"kirti",9000);
        when(employeeRepository.save(ExpectedResult)).thenReturn(ExpectedResult);
        Employee ActualResult = employeeServiceImpl.saveEmployee(ExpectedResult);
        assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    void getAllEmployeeTest(){
        List<Employee> ExpectedResult = new ArrayList<>();
        ExpectedResult.add(new Employee(1,"neha",9000));
        ExpectedResult.add(new Employee(2,"jaya",8000));
        when(employeeRepository.findAll()).thenReturn(ExpectedResult);
        List<Employee> ActualResult = employeeServiceImpl.getAllEmployee();
        assertEquals(ExpectedResult, ActualResult);
    }
    
    @Test
    void getEmployeeIdTest(){
       Employee ExpectedResult = new Employee(1,"neha",9000);
       when(employeeRepository.findById(1)).thenReturn(Optional.of(ExpectedResult));
       Optional<Employee> ActualResult= employeeServiceImpl.getEmployeeId(1);
       assertTrue(ActualResult.isPresent());
       assertEquals(ExpectedResult, ActualResult.get());
    }


    @Test
    void updateEmployeeTest(){
        Employee ExpectedResult = new Employee(1,"neha",9000);
        when(employeeRepository.save(ExpectedResult)).thenReturn(ExpectedResult);
        Employee ActualResult = employeeServiceImpl.updateEmployee(ExpectedResult);
        assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    void deleteEmployeeTest(){
        int id=1;
        employeeRepository.deleteById(id);
        verify(employeeServiceImpl,times(1)).deleteEmployee(id);
    }
}
