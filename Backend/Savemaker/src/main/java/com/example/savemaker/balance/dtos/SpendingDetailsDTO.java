package com.example.savemaker.balance.dtos;

import com.example.savemaker.balance.models.SpendingDetails;

public class SpendingDetailsDTO {
    private String categoryName;
    private Double amount;

    public SpendingDetailsDTO() {}

    public SpendingDetailsDTO(SpendingDetails spendingDetails) {
        this.categoryName = spendingDetails.getCategory().getName();
        this.amount = spendingDetails.getAmount();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
