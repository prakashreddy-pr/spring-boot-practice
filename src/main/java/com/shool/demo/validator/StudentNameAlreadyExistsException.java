package com.shool.demo.validator;

public class StudentNameAlreadyExistsException extends RuntimeException {
    public StudentNameAlreadyExistsException(String message) {
        super(message);
    }
}
