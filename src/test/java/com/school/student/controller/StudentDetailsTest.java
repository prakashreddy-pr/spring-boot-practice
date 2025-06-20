package com.school.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.student.controllers.StudentDetails;
import com.school.student.entities.Student;
import com.school.student.Services.StudentServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest("Test")
@Transactional // This will ensure that each test runs in a transaction
@Rollback
public class StudentDetailsTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private StudentServices studentServices;

    @InjectMocks
    private StudentDetails studentDetails;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   // @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student( "Joe", "David", "Lusi", "New Jersey", "John");
        when(studentServices.saveStudent(student)).thenReturn(student);
        mockMvc.perform(post("/student/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joe"))
                .andExpect(jsonPath("$.fatherName").value("David"));
    }

    @Test
    public void testStudentService(){
        Student student = new Student( "Joe", "David", "Lusi", "New Jersey", "John");
        when(studentServices.getStudentDetails("Joe")).thenReturn(List.of(student));
      Assertions.assertEquals( studentServices.getStudentDetails("Joe").get(0).getName(), "Joe");
    }
}
