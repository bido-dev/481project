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
public class Graduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer graduateId;

    @NotEmpty(message = "Graduate name must not be empty")
    private String graduateName;

    private String status; // consider Enum later

    // Each graduate specializes at ONE major
    @ManyToOne
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    // Graduate <-> Advice (N:M)  "Give"
    @ManyToMany
    @JoinTable(
            name = "graduate_advice",
            joinColumns = @JoinColumn(name = "graduate_id"),
            inverseJoinColumns = @JoinColumn(name = "advice_id")
    )
    private Set<Advice> advices = new HashSet<>();

    // Graduate -> StudentExperience (1:N)  "Write"
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentExperience> experiences = new HashSet<>();
}