package com.school.student.repositories;

import com.school.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByName(String name);
    List<Student> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);  // Custom query to check for duplicates

}
