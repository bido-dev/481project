package com.example.swe481.Service;

import com.example.swe481.Model.Student;
import com.example.swe481.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void registerStudent(Student student) {
        studentRepository.save(student);
    }
    public void login(String email, String rawPassword) {
        Student student = studentRepository.findByStudentEmail(email);

        if (student == null) {
            throw new RuntimeException("Email not found");
        }

        if (!rawPassword.equals(student.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

    }

}
