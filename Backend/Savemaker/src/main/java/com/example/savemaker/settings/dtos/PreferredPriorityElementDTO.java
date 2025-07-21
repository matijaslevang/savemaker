package com.example.savemaker.settings.dtos;

import com.example.savemaker.transactions.dtos.CategoryDTO;
import com.example.savemaker.transactions.models.Category;

public class PreferredPriorityElementDTO {
    private Long spendingCategoryId;
    private String spendingCategoryName;
    private CategoryDTO preferredIncomeCategory;

    public PreferredPriorityElementDTO() {}

    public PreferredPriorityElementDTO(Category category) {
        this.spendingCategoryId = category.getId();
        this.spendingCategoryName = category.getName();
        this.preferredIncomeCategory = new CategoryDTO(category.getPreferredSpendingCategory());
    }

    public Long getSpendingCategoryId() {
        return spendingCategoryId;
    }

    public void setSpendingCategoryId(Long spendingCategoryId) {
        this.spendingCategoryId = spendingCategoryId;
    }

    public String getSpendingCategoryName() {
        return spendingCategoryName;
    }

    public void setSpendingCategoryName(String spendingCategoryName) {
        this.spendingCategoryName = spendingCategoryName;
    }

    public CategoryDTO getPreferredIncomeCategory() {
        return preferredIncomeCategory;
    }

    public void setPreferredIncomeCategory(CategoryDTO preferredIncomeCategory) {
        this.preferredIncomeCategory = preferredIncomeCategory;
    }
}
