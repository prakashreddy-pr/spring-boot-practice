package com.school.student.validator;

import com.school.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueClassNameValidator implements ConstraintValidator<UniqueClassName, String> {

    @Autowired
    private StudentRepository studentRepository;  // Inject the repository

    @Override
    public void initialize(UniqueClassName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        // Check if the username already exists in the database
        System.out.println("exist boolean status " +studentRepository.existsByName(name));
        return !studentRepository.existsByName(name);
    }
}
