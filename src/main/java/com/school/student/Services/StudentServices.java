package com.school.student.Services;

import com.school.student.entities.Student;
import com.school.student.repositories.StudentRepository;
import com.school.student.validator.StudentNameAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;

@Service
@EnableKafka
public class StudentServices {

    @Autowired
    private KafkaTemplate<String, String> kafkaTopic;
    @Autowired
    private StudentRepository studentRepository;
    private final Random random = new Random();

    // Log4j log4j4

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
        Student student1 = studentRepository.save(student);
        System.out.println(" Type of object  " + student1.getClass().getName());
        System.out.println(student1);
        kafkaTopic.send("saveStudent", "Message from Kfka Topic");
        return student1;
    }

    @KafkaListener(topics = "saveStudent", groupId = "my-group-id")
    public void kafkaListener(String message) {
        System.out.println("Message received " + message);
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

    public List<Student> getStudentDetails(String name) {
        return studentRepository.findByName(name);
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
