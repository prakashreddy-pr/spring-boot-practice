package com.school.student.repositories;

import com.school.student.entities.ClassEntity;
import com.school.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {
   List<ClassEntity> findByClassNameContainingIgnoreCase(String name);
    boolean existsByClassName(String name);
    ClassEntity findByClassName(String name);
    int deleteByClassName(String name);
    List<ClassEntity> findAll();

}
