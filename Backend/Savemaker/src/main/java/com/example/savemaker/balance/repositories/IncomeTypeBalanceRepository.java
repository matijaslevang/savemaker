package com.example.savemaker.balance.repositories;

import com.example.savemaker.balance.models.IncomeTypeBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTypeBalanceRepository extends JpaRepository<IncomeTypeBalance, Long> {

    @Query("SELECT b from IncomeTypeBalance b WHERE b.incomeCategory.id = :categoryId ")
    IncomeTypeBalance findByIncomeCategory(@Param("categoryId") Long categoryId);
}
