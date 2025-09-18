package com.example.swe481.Model;


import jakarta.persistence.*;
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
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer majorId;

    @NotEmpty(message = "Major name must not be empty")
    private String majorName;

    private String majorOverview;
    private String majorCategory;

    // University <-> Major (N:M)
    @ManyToMany
    @JoinTable(
            name = "university_major",
            joinColumns = @JoinColumn(name = "major_id"),
            inverseJoinColumns = @JoinColumn(name = "university_id")
    )
    private Set<University> universities = new HashSet<>();

    // Major -> Advices (1:N)
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Advice> advices = new HashSet<>();

    // Major -> StudentExperience (1:N)
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentExperience> experiences = new HashSet<>();

    // Major -> Graduates (1:N)  (each Graduate specializes at one Major)
    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Graduate> graduates = new HashSet<>();

    // Student <-> Major (N:M)  "Search"
    @ManyToMany(mappedBy = "searchedMajors")
    private Set<Student> students = new HashSet<>();
}
