package com.Bnt.EmployeeManagementUsingJpa.Controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.Bnt.EmployeeManagementUsingJpa.Service.EmployeeServiceImpl;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    
@Mock
EmployeeServiceImpl employeeService;
     
@InjectMocks
EmployeeController employeeController;

@Test
void saveEmployeeTest(){
    Employee ExpectedResult = new Employee(1,"Vijaya",2000);
    when(employeeService.saveEmployee(ExpectedResult)).thenReturn(ExpectedResult);
    ResponseEntity<Object> ActualResponseEntity = employeeController.saveEmployee(ExpectedResult);
    assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
    assertEquals(ExpectedResult, ActualResponseEntity.getBody());
}

@Test
void getAllEmployeeTest(){
    List<Employee> ExpectedResult = new ArrayList<>();
    ExpectedResult.add(new Employee(1,"Vijaya",5000));
    ExpectedResult.add(new Employee(2,"kirtika",9000));
    when(employeeService.getAllEmployee()).thenReturn(ExpectedResult);
    List<Employee> ActualResult = employeeController.getAllEmployee();
    assertEquals(ExpectedResult, ActualResult);
}

@Test
void getEmployeeIdTest(){
       int id=1;
       Optional<Employee> ExpectedResult = Optional.empty();
       when(employeeService.getEmployeeId(id)).thenReturn(ExpectedResult);
       ResponseEntity<Object> ActualResult = employeeController.getEmployeeId(1);
       assertSame(HttpStatus.OK, ActualResult.getStatusCode());
       assertSame(ExpectedResult, ActualResult.getBody());       
    }


@Test
void updateEmployeeTest(){
    Employee ExpectedResult = new Employee(1,"Vijaya",2000);
    when(employeeService.updateEmployee(ExpectedResult)).thenReturn(null);
    ResponseEntity<Object> ActualResponseEntity = employeeController.updateEmployee(ExpectedResult.getId(),ExpectedResult);
    assertEquals(HttpStatus.NOT_FOUND, ActualResponseEntity.getStatusCode());
    assertEquals("User not found this id", ActualResponseEntity.getBody());
}

@Test
void deleteEmployeeTest(){
    int id=1;
    when(employeeService.deleteEmployee(id)).thenReturn(false);
    ResponseEntity<Object> ActualResponseEntity = employeeController.deleteEmployee(id);
    assertEquals(HttpStatus.NOT_FOUND, ActualResponseEntity.getStatusCode());
    assertEquals("UserNotFound With id", ActualResponseEntity.getBody());
}

}
