package com.example.savemaker.transactions.dtos;

import com.example.savemaker.balance.dtos.SpendingDetailsDTO;
import com.example.savemaker.transactions.models.Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionDTO {
    private Long id;
    private CategoryDTO category;
    private String notes;
    private Double amount;
    private List<SpendingDetailsDTO> spendingDetails;
    private LocalDate date;

    public TransactionDTO() {}

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.category = new CategoryDTO(transaction.getCategory());
        this.notes = transaction.getNotes();
        this.spendingDetails = transaction.getSpendingDetails().stream().map(v -> new SpendingDetailsDTO(v)).toList();
        this.amount = 0.0;
        transaction.getSpendingDetails().forEach(v -> this.amount += v.getAmount());
        this.date = transaction.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
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

    public List<SpendingDetailsDTO> getSpendingDetails() {
        return spendingDetails;
    }

    public void setSpendingDetails(List<SpendingDetailsDTO> spendingDetails) {
        this.spendingDetails = spendingDetails;
    }
}
