package com.studentManagement.Service;

import java.util.List;
import java.util.Optional;

import com.studentManagement.Model.Student;

public interface StudentService {
     public Student save(Student student);
     public List<Student> getall();
     public Optional<Student> getid(Long id);
     public Student update(Student student);
     public void delete(Long id);
}
