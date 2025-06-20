package com.school.student.validator;

import com.school.student.entities.BadRequest;
import com.school.student.entities.Student;
import jakarta.validation.Constraint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
public class RequestValidator {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentNameAlreadyExistsException.class)
    public ResponseEntity<BadRequest> handleStudentNameAlreadyExistsException(StudentNameAlreadyExistsException ex) {
        BadRequest badRequest = new BadRequest();
        badRequest.setErrorMessage(ex.getMessage());
        badRequest.setField("name");
        badRequest.setErrorCode("66666");
        return  ResponseEntity.badRequest().body(badRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassNameAlreadyExistsException.class)
    public ResponseEntity<BadRequest> handleClassNameAlreadyExists(ClassNameAlreadyExistsException ex){
        BadRequest badRequest = new BadRequest();
        badRequest.setErrorCode("12345");
        badRequest.setErrorMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(badRequest);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequest> validator (MethodArgumentNotValidException ex){
        BadRequest requestFail = new BadRequest();
        ex.getBindingResult().getAllErrors().forEach(k->{
            System.out.println("Errors "+ k);
           requestFail.setField(((FieldError) k).getField());
           requestFail.setErrorMessage(k.getDefaultMessage());

        //   requestFail.setErrorCode(k.getCode());
           System.out.println( " Ex is -----"+ex.toString());
//           System.out.println("Default message "+ k.getDefaultMessage()  +"  "+ Arrays.toString(k.getCodes()));
//           System.out.println("Objectname " + k.getObjectName());
//          System.out.println ("Rejected   " + ((FieldError) k).getRejectedValue());
            try {
                // Assuming your DTO is StudentDTO
                Field field = Student.class.getDeclaredField(((FieldError) k).getField());
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
                        String messageKey = (String) annotation.annotationType()
                                .getMethod("message")
                                .invoke(annotation);
                        requestFail.setErrorCode( messageKey); // Store the message key
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace(); // Handle reflection issues
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });

       return  ResponseEntity.badRequest().body(requestFail);
    }
}
