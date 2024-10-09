package com.school.student.controllers;

import com.school.student.DTO.StudentDTO;
import com.school.student.Services.StudentServices;
import com.school.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentDetails {

    @Autowired
    StudentServices studentServices;

    @PostMapping(value = "/student/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@Validated @RequestBody Student student) {
        Student createdStudent = studentServices.saveStudent(student);
        return ResponseEntity.ok(createdStudent); // 201 Created
    }

    @GetMapping(value = "/student/{name}/details", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getStudentDetails(@PathVariable String name) {
        return ResponseEntity.status(200).body(studentServices.getStudentDetails(name));
    }

    @GetMapping("/student/all/student/{name}/details")
    public List<Student> getAllStudentDetails(@PathVariable String name) {
        return studentServices.getAllStudentDetails(name);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testCircuitBreaker() {
        return studentServices.testCircuitBreaker();
    }
}
