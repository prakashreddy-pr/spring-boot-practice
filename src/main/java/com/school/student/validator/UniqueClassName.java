package com.school.student.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueClassNameValidator.class) // Link the validator class
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueClassName {

    String message() default "Name already exists";  // Custom error message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
