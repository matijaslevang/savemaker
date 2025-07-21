package com.example.savemaker.transactions.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category that = (Category) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
