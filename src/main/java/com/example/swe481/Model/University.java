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
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer universityId;

    @NotEmpty(message = "University name must not be empty")
    private String universityName;

    private String universityCity;

    // University <-> Major (N:M)
    @ManyToMany(mappedBy = "universities")
    private Set<Major> majors = new HashSet<>();
}