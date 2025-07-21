package com.example.savemaker.transactions.repositories;


import com.example.savemaker.transactions.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c from Category c WHERE c.isUsedForIncome = :usedForIncome")
    List<Category> findAllByUsedForIncome(@Param("usedForIncome") Boolean usedForIncome);
}
