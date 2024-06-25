package com.studentManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentManagement.Model.Student;
import com.studentManagement.Service.StudentServiceImpl;

@RestController
@RequestMapping("/Student")
public class StudentController {

 @Autowired
 StudentServiceImpl studentService;
 
 @GetMapping("/hello")
 public String hello(){
    return "Hello this is simple demo";
 }
    
@PostMapping("/save")
public Student save(@RequestBody Student student){
  return  studentService.save(student);
}

@GetMapping
public List<Student> getall(){
    return studentService.getall();
}

@GetMapping("/{id}")
public Optional<Student> getid(@PathVariable ("id") Long id ){
    return studentService.getid(id);
}

@PutMapping
public Student update(@RequestBody Student student){
    return studentService.update(student);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable ("id") Long id){
     studentService.delete(id);
}
}
