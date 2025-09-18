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
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adviceId;

    @NotEmpty(message = "Advice content must not be empty")
    @Column(columnDefinition = "TEXT")
    private String adviceContent;

    // Many advices belong to one Major
    @ManyToOne
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    // Graduate <-> Advice (N:M)  "Give"
    @ManyToMany(mappedBy = "advices")
    private Set<Graduate> graduates = new HashSet<>();
}
