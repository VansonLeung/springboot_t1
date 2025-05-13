package com.vp.springbootsample.services.impl;

import com.vp.springbootsample.dtos.StudentDTO;
import com.vp.springbootsample.entities.Student;
import com.vp.springbootsample.repositories.interfaces.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getAllStudents(Specification<Student> spec, Pageable pageable) {
        return studentRepository.findAll(spec, pageable);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getAge());
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}