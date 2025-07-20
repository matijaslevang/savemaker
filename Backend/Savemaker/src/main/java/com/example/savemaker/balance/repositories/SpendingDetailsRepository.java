package com.example.savemaker.balance.repositories;

import com.example.savemaker.balance.models.SpendingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingDetailsRepository extends JpaRepository<SpendingDetails, Long> {
}
