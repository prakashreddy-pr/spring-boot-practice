package com.school.student.dto;

import java.util.UUID;

public class StudentDTO {
    private UUID uuid;
    private String name;

    public StudentDTO() {
    }

    public StudentDTO(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
