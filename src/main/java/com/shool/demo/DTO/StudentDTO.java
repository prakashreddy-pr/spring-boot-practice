package com.shool.demo.DTO;

import java.util.UUID;

public class StudentDTO {
    private UUID uuid;
    private String name;
    private String fatherName;
    private String motherName;
    private String address;
    private String teacher;


    // Constructors, getters, and setters
    public StudentDTO() {}

    public StudentDTO(UUID id, String name, String fatherName, String motherName, String address, String teacher) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.address = address;
        this.teacher = teacher;
        this.uuid = id;
    }
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    // Getters and Setters...
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
}

