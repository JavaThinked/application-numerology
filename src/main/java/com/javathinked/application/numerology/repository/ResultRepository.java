package com.javathinked.application.numerology.repository;

import com.javathinked.application.numerology.service.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT r FROM Result r WHERE r.category = :category AND r.number = :number AND r.language = :language")
    Result findResultByCategoryAndNumberAndLanguage(@Param("category")String category, @Param("number") int number, @Param("language") String language);
}
