package com.studentManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagement.Model.Student;
import com.studentManagement.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository StudentRepository;

    @Override
    public Student save(Student student) {
    return StudentRepository.save(student);
    }

    @Override
    public List<Student> getall() {
        return StudentRepository.findAll();
    }

    @Override
    public Optional<Student> getid(Long id) {
        return StudentRepository.findById(id);
    }

    @Override
    public Student update(Student student) {
    return StudentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        StudentRepository.deleteById(id);
    }

    
}
