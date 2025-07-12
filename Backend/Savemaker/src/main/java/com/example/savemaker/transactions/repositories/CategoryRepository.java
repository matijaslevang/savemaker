package com.example.savemaker.transactions.repositories;


import com.example.savemaker.transactions.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
