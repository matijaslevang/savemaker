package com.example.savemaker.transactions.models;

import com.example.savemaker.balance.models.SpendingDetails;

import java.time.LocalDate;
import java.util.List;

public class Transaction {

    private Long id;
    private Category category;
    private String notes;
    private Double amount;
    private List<SpendingDetails> spendingDetails;
    private LocalDate date;

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<SpendingDetails> getSpendingDetails() {
        return spendingDetails;
    }

    public void setSpendingDetails(List<SpendingDetails> spendingDetails) {
        this.spendingDetails = spendingDetails;
    }
}
