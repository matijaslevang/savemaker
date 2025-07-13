package com.example.savemaker.transactions.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    private Long id;
    private String name;
    @JsonProperty("isUsedForIncome")
    private Boolean isUsedForIncome;

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

    @Override
    public String toString() {
        return name;
    }
}
