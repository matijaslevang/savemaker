package com.example.savemaker.transactions.repositories;

import com.example.savemaker.transactions.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop3ByOrderByIdDesc();

    @Query("SELECT t FROM Transaction t WHERE (:categoryId IS NULL OR t.category.id = :categoryId) " +
            "AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> getAll(@Param("categoryId") Long categoryId,
                             @Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate);
}
