package com.example.savemaker.transactions.models;

import com.example.savemaker.balance.models.SpendingDetails;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "spending_details_transaction_table",
               joinColumns = @JoinColumn(name = "transaction_id"),
               inverseJoinColumns = @JoinColumn(name = "spending_details_id"))
    private List<SpendingDetails> spendingDetails;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SpendingDetails> getSpendingDetails() {
        return spendingDetails;
    }

    public void setSpendingDetails(List<SpendingDetails> spendingDetails) {
        this.spendingDetails = spendingDetails;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getTotalAmount() {
        return this.spendingDetails.stream().mapToDouble(SpendingDetails::getAmount).sum();
    }
}
