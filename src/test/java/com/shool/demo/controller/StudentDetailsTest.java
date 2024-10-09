package com.shool.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shool.demo.Services.StudentServices;
import com.shool.demo.controllers.StudentDetails;
import com.shool.demo.entities.Student;
import jakarta.transaction.Transactional;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.POST;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student( "Joe", "David", "Lusi", "New Jersey", "John");
        //System.out.println(student.toString());
        mockMvc.perform(post("/student/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joe"))
                .andExpect(jsonPath("$.fatherName").value("David"));
    }
}
