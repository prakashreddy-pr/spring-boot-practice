package com.shool.demo.entities;

import com.shool.demo.validator.UniqueName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @NotBlank(message = "{SC-00001}")
    @UniqueName
    private String name;
   // @Size(max = 3, message = "{SC-00002}")
    private String fatherName;
    private String motherName;
    private String address;
    private String teacher;
    private int version;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + uuid +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", address='" + address + '\'' +
                ", teacher='" + teacher + '\'' +
                ", version="+version +
                '}';
    }

    public Student() {
    }

    // Parameterized constructor
    public Student( String name, String fatherName, String motherName, String address, String teacher) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.address = address;
        this.teacher = teacher;
      //  this.uuid = uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }
    public UUID getUuid(){
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
}
