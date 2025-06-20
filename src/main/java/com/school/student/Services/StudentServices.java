package com.school.student.Services;

import com.school.student.entities.ClassEntity;
import com.school.student.entities.Student;
import com.school.student.repositories.ClassRepository;
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
    private KafkaTemplate<String, Student> kafkaTopic;
    @Autowired
    private StudentRepository studentRepository;
    private final Random random = new Random();
    @Autowired
    ClassRepository classRepository;

    // Log4j log4j4

    @Transactional
    public Student saveStudent(Student student) {
        if (studentRepository.existsByName(student.getName()))
            throw new StudentNameAlreadyExistsException("Student name already exists: " + student.getName());
        ClassEntity classEntity = classRepository.findByClassName("8th Standard");
        student.setVersion(1);
        student.setClassEntity(classEntity);
        Student student1 = studentRepository.save(student);
        kafkaTopic.send("saveStudent", student1);
        return student1;
    }

    @KafkaListener(topics = "saveStudent", groupId = "my-group-2")
    public void kafkaListener(Student student) {
        System.out.println("Message received student service class" + student);
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
