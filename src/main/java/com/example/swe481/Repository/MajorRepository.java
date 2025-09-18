package com.example.swe481.Repository;

import com.example.swe481.Model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
}
