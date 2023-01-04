package com.javathinked.application.numerology.repository;

import com.javathinked.application.numerology.service.model.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Long> {

    Formula findByCategory(String category);
}
