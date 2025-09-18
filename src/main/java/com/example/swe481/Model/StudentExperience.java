package com.example.swe481.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentExperienceId;

    @NotEmpty(message = "Experience content must not be empty")
    @Column(columnDefinition = "TEXT")
    private String studentExperienceContent;

    // belongs to one Major
    @ManyToOne
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    // written by one Graduate
    @ManyToOne
    @JoinColumn(name = "graduate_id", nullable = false)
    private Graduate author;
}
