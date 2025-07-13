package com.example.savemaker.transactions.models;

import java.time.LocalDate;

public class CreateTransaction {
    private LocalDate date;
    private Double amount;
    private String notes;
    private Long categoryId;

    public CreateTransaction() {}

    public CreateTransaction(LocalDate date, Double amount, String notes, Long categoryId) {
        this.date = date;
        this.amount = amount;
        this.notes = notes;
        this.categoryId = categoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
