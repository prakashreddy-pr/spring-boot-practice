package com.school.student.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.student.validator.UniqueName;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "class_table")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @UniqueName
    @JsonProperty("className")
    private String className;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> students;

    // Constructors, Getters, Setters
    public ClassEntity() {
    }

    public ClassEntity(String className) {
        this.className = className;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
