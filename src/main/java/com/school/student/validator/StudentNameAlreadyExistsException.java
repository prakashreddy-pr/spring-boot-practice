package com.school.student.validator;

public class StudentNameAlreadyExistsException extends RuntimeException {
    public StudentNameAlreadyExistsException(String message) {
        super(message);
    }
}
