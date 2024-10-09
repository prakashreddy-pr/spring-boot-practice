package com.shool.demo.Services;

import com.shool.demo.DTO.StudentDTO;
import com.shool.demo.entities.Student;
import com.shool.demo.repositories.StudentRepository;
import com.shool.demo.validator.StudentNameAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;
import java.util.Random;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;
    private Random random = new Random();

    @Transactional
    public Student saveStudent(Student student) {
//        Student student = new Student();
//        student.setName(studentDTO.getName());
//        student.setFatherName(studentDTO.getFatherName());
//        student.setMotherName(studentDTO.getMotherName());
//        student.setAddress(studentDTO.getAddress());
//        student.setTeacher(studentDTO.getTeacher());
//        student.setVersion(1);
        if (studentRepository.existsByName(student.getName())) {
            throw new StudentNameAlreadyExistsException("Student name already exists: " + student.getName());
        }
        student.setVersion(1);
        return studentRepository.save(student);
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackMethod")
    public ResponseEntity<String> testCircuitBreaker() {
        if (random.nextInt(10) < 7) { // 70% chance of failure
            System.out.println("Request failed");
        }
        return ResponseEntity.ok().body("Request Successful ");
    }

    public String fallbackMethod(Throwable t) {
        System.out.println("Fall back method called");
        return "Fallback: Service is temporarily unavailable.";
    }

    public StudentDTO getStudentDetails(String name) {
        //   System.out.println("Student details " + studentRepository.getReferenceById(id));
        Student student = (Student) studentRepository.findByName(name);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setTeacher(student.getTeacher());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setFatherName(student.getFatherName());
        studentDTO.setMotherName(student.getMotherName());
        studentDTO.setName(student.getName());
        studentDTO.setUuid(student.getUuid());
        return studentDTO;
    }

    public List<Student> getAllStudentDetails(String name) {
        System.out.println("Student details " + studentRepository.findByNameContainingIgnoreCase(name));
        return studentRepository.findByNameContainingIgnoreCase(name);
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setTeacher(student.getTeacher());
//        studentDTO.setAddress(student.getAddress());
//        studentDTO.setFatherName(student.getFatherName());
//        studentDTO.setMotherName(student.getMotherName());
//        studentDTO.setName(student.getName());
//        studentDTO.setId(student.getId());
//        return studentDTO;
    }
}
