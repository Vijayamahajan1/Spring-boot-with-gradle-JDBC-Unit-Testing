package com.Bnt.EmployeeManagementUsingJpa.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
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
        Employee ExpectedResult = new Employee(16,"neha",9000);
        when(employeeRepository.findById(ExpectedResult.getId())).thenReturn(Optional.of(ExpectedResult));
        when(employeeRepository.save(ExpectedResult)).thenReturn(ExpectedResult);
        Employee ActualResult = employeeServiceImpl.updateEmployee(ExpectedResult);
        assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    void deleteEmployeeTest(){
        int id=1;
        when(employeeRepository.existsById(id)).thenReturn(true);
        boolean result = employeeServiceImpl.deleteEmployee(id);
        assertTrue(result);
        verify(employeeRepository,times(1)).deleteById(id);
    }

    //--------------------------------Negative Test Cases-----------------------------------------//

    @Test
    void saveEmployeeTest_InvalidData(){
        Employee invalidEmployee1 = new Employee(1, "", 5000);
        Employee invalidEmployee2 = new Employee(1, "abhay", 0);
        Employee result1 = employeeServiceImpl.saveEmployee(invalidEmployee1);
        Employee result2 = employeeServiceImpl.saveEmployee(invalidEmployee2);
        assertNull(result1);
        assertNull(result2);
    }

    @Test
    void getAllEmployeeTest_negative(){
      when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
      List<Employee> alleEmployees = employeeServiceImpl.getAllEmployee();
      assertTrue(alleEmployees.isEmpty());
    }
   
    @Test
    void getEmployeeIdTest_negative(){
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<Employee> result =  employeeServiceImpl.getEmployeeId(99);
        assertFalse(result.isPresent());
    }

    @Test
    void updateEmployeeTest_Negative(){
        int notPresentId = 77;
        Employee newEmployee =new Employee();
        newEmployee.setId(notPresentId);
        when(employeeRepository.findById(notPresentId)).thenReturn(Optional.empty());
        Employee updatedEmployee = employeeServiceImpl.updateEmployee(newEmployee);
        assertNull(updatedEmployee);
    }

    @Test
    void deleteEmployeeTest_Negative(){
        int notPresentId = 77;
        when(employeeRepository.existsById(notPresentId)).thenReturn(false);
        boolean result = employeeServiceImpl.deleteEmployee(notPresentId);
        assertFalse(result);
        verify(employeeRepository, never()).deleteById(notPresentId);
    }
}
