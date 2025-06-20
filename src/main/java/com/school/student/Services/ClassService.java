package com.school.student.Services;

import com.school.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ClassService {

  @Autowired
    private WebClient webClient ;

    public Student saveStudent(Student student){

    return  webClient.post()
                .uri("http://localhost:3030/student/save")
            .bodyValue(student)
            .retrieve()
            .bodyToMono(Student.class)
            .block();  // ❗Blocks thread — use only in non-reactive code
    }
}
