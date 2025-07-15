package com.example.savemaker.balance.models;

import com.example.savemaker.transactions.models.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "income_type_balances")
public class IncomeTypeBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category incomeCategory;

    @Column(nullable = false)
    private Double balance;

    public IncomeTypeBalance() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(Category incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
