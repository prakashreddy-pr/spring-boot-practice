package com.school.student.Services;

import com.school.student.entities.ClassEntity;
import com.school.student.entities.Student;
import com.school.student.repositories.ClassRepository;
import com.school.student.validator.ClassNameAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class ClassService {

  @Autowired
    private WebClient webClient ;

  @Autowired
    private ClassRepository classRepository;

  @Transactional
  public ClassEntity saveClass(ClassEntity classEntity){
    if(classRepository.existsByClassName(classEntity.getClassName()))
      throw new ClassNameAlreadyExistsException("Class name already exist "+classEntity.getClassName());
    classRepository.save(classEntity);
    return classRepository.findByClassName(classEntity.getClassName());
  }

  public List<ClassEntity> getClass(String className){
    return classRepository.findByClassNameContainingIgnoreCase(className);
  }

  @Transactional
  public String deleteClass(String className){
    if(classRepository.deleteByClassName(className) > 0)
      return "Class deleted successfully";
    else
      return "Deletion failed try again";
  }


    public Student saveStudent(Student student){

    return  webClient.post()
                .uri("http://localhost:3030/student/save")
            .bodyValue(student)
            .retrieve()
            .bodyToMono(Student.class)
            .block();  //
    }
}
