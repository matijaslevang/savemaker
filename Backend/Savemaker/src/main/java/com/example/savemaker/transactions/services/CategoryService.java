package com.example.savemaker.transactions.services;

import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
