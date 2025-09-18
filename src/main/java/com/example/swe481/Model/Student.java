package com.example.swe481.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Student name must not be empty")
    private String studentsName;

    @Email(message = "Invalid email")
    private String studentEmail;

    // Student <-> Major (N:M)  "Search"
    @ManyToMany
    @JoinTable(
            name = "student_search_major",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private Set<Major> searchedMajors = new HashSet<>();
}
