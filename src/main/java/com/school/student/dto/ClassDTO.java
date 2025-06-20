package com.school.student.dto;

import java.util.List;
import java.util.UUID;

public class ClassDTO {
    private UUID id;
    private String className;
    private List<StudentDTO> students;

    public ClassDTO() {
    }

    public ClassDTO(UUID id, String className, List<StudentDTO> students) {
        this.id = id;
        this.className = className;
        this.students = students;
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

    public List<com.school.student.dto.StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<com.school.student.dto.StudentDTO> students) {
        this.students = students;
    }
}
