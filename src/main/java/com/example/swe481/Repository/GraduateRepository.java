package com.example.swe481.Repository;

import com.example.swe481.Model.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduateRepository extends JpaRepository<Graduate, Integer> {
}
