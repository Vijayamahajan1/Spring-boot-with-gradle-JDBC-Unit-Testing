package com.Bnt.EmployeeManagementUsingJpa.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeTest {
    @InjectMocks
    Employee employee;
     
    @Test
    void setgetIdTest(){
     Employee ExpectedResult = new Employee();
     ExpectedResult.setId(2);
     assertEquals(2, ExpectedResult.getId());
    }

    @Test
    void setgetName(){
        Employee ExpectedResult = new Employee();
        ExpectedResult.setName("vijaya");
        assertEquals("vijaya", ExpectedResult.getName());
    }
    @Test
    void setgetSalary(){
        Employee ExpectedResult = new Employee();
        ExpectedResult.setSalary(7000);
        assertEquals(7000, ExpectedResult.getSalary());
    }
}
