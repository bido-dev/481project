package com.example.swe481.Controller;

import com.example.swe481.Api.ApiResponse;
import com.example.swe481.Model.Student;
import com.example.swe481.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody Student student) {
        studentService.registerStudent(student);
        return ResponseEntity.ok().body(new ApiResponse("Student registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam  String email, @RequestParam String password) {
        studentService.login(email, password);
        return ResponseEntity.ok().body(new ApiResponse("Login successfully"));
    }



}
