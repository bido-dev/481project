package com.example.swe481.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @NotEmpty(message = "Username must not be empty")
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @NotEmpty(message = "Student name must not be empty")
    private String studentName;

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email must not be empty")
    @Column(unique = true, nullable = false)
    private String studentEmail;

    @NotEmpty(message = "Password must not be empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters, include uppercase, lowercase, number, and special character"
    )
    @Column(nullable = false)
    private String password;


    // Student <-> Major (N:M)  "Search"
    @ManyToMany
    @JoinTable(
            name = "student_search_major",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private Set<Major> searchedMajors = new HashSet<>();
}
