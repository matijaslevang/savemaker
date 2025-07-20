package com.example.savemaker.balance.models;

public class SpendingDetails {
    private String categoryName;
    private Double amount;

    public SpendingDetails() {}

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
