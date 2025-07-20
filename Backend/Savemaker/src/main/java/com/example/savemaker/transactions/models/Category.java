package com.example.savemaker.transactions.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isUsedForIncome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "preferred_spending_category_id", referencedColumnName = "id")
    private Category preferredSpendingCategory;

    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUsedForIncome() {
        return isUsedForIncome;
    }

    public void setUsedForIncome(Boolean usedForIncome) {
        isUsedForIncome = usedForIncome;
    }

    public Category getPreferredSpendingCategory() {
        return preferredSpendingCategory;
    }

    public void setPreferredSpendingCategory(Category preferredSpendingCategory) {
        this.preferredSpendingCategory = preferredSpendingCategory;
    }
}
