package com.school.student.validator;

public class ClassNameAlreadyExistsException extends RuntimeException {
    public ClassNameAlreadyExistsException(String message) {
        super(message);
    }
}
