package com.studentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentManagement.Model.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
