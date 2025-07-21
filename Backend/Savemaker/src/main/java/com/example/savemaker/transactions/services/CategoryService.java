package com.example.savemaker.transactions.services;

import com.example.savemaker.settings.dtos.PreferredPriorityElementDTO;
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

    public List<Category> getAllByUsedForIncome(Boolean usedForIncome) {
        return categoryRepository.findAllByUsedForIncome(usedForIncome);
    }

    public Boolean updatePreferredPriorities(List<PreferredPriorityElementDTO> dtos) {
        for (PreferredPriorityElementDTO dto : dtos) {
            Category current = getCategory(dto.getSpendingCategoryId());
            if (current == null || dto.getPreferredIncomeCategory() == null) return false;
            if (!dto.getPreferredIncomeCategory().getUsedForIncome()) return false;

            current.setPreferredSpendingCategory(getCategory(dto.getPreferredIncomeCategory().getId()));
            categoryRepository.save(current);
        }
        return true;
    }

}
