package com.vp.springbootsample.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vp.springbootsample.dtos.StudentDTO;
import com.vp.springbootsample.entities.Student;
import com.vp.springbootsample.repositories.interfaces.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        studentRepository.deleteAll(); // Clear database before each test
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO("John Doe", "john@example.com", 20);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = new Student("Jane Doe", "jane@example.com", 22);
        studentRepository.save(student);

        mockMvc.perform(get("/api/students/{id}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student("Mark Smith", "mark@example.com", 25);
        studentRepository.save(student);

        StudentDTO updatedStudentDTO = new StudentDTO("Mark Updated", "mark.updated@example.com", 26);

        mockMvc.perform(put("/api/students/{id}", student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mark Updated"))
                .andExpect(jsonPath("$.email").value("mark.updated@example.com"))
                .andExpect(jsonPath("$.age").value(26));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student("Alice Brown", "alice@example.com", 23);
        studentRepository.save(student);

        mockMvc.perform(delete("/api/students/{id}", student.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/students/{id}", student.getId()))
                .andExpect(status().isNotFound());
    }
}