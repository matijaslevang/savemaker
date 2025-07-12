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

    public Category() {}

    public Category(Long id, String name, Boolean isUsedForIncome) {
        this.id = id;
        this.name = name;
        this.isUsedForIncome = isUsedForIncome;
    }

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
}
