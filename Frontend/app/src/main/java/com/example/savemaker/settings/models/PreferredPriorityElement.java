package com.example.savemaker.settings.models;

import com.example.savemaker.transactions.models.Category;

public class PreferredPriorityElement {
    private Long spendingCategoryId;
    private String spendingCategoryName;
    private Category preferredIncomeCategory;

    public PreferredPriorityElement() {}

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

    public Category getPreferredIncomeCategory() {
        return preferredIncomeCategory;
    }

    public void setPreferredIncomeCategory(Category preferredIncomeCategory) {
        this.preferredIncomeCategory = preferredIncomeCategory;
    }
}
