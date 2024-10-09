package com.shool.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shool.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private StudentRepository studentRepository;  // Inject the repository

    @Override
    public void initialize(UniqueName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        // Check if the username already exists in the database
        System.out.println("exist boolean status " +studentRepository.existsByName(name));
        return !studentRepository.existsByName(name);
    }
}
