package com.example.savemaker.balance.repositories;

import com.example.savemaker.balance.models.MainBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainBalanceRepository extends JpaRepository<MainBalance, Long> {
}
