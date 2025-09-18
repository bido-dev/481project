package com.example.swe481.Repository;

import com.example.swe481.Model.StudentExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExperienceRepository extends JpaRepository<StudentExperience, Integer> {
}
