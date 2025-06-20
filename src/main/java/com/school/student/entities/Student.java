package com.school.student.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.student.validator.UniqueName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("uuid")
    private UUID uuid;
    @NotBlank(message = "{SC-00001}")
    @UniqueName
    @JsonProperty("name")
    private String name;
    // @Size(max = 3, message = "{SC-00002}")
    @JsonProperty("fatherName")
    private String fatherName;
    @JsonProperty("motherName")
    private String motherName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("teacher")
    private String teacher;
    @JsonProperty("version")
    private int version;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    @JsonProperty("class")
    private ClassEntity classEntity;

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }


    public Student() {
    }

    // Parameterized constructor
    public Student(String name, String fatherName, String motherName, String address, String teacher) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.address = address;
        this.teacher = teacher;
        //  this.uuid = uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    // Getters and Setters...
    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", address='" + address + '\'' +
                ", teacher='" + teacher + '\'' +
                ", version=" + version +
                ", classEntityId=" + (classEntity != null ? classEntity.getId() : null) +
                '}';
    }

}

