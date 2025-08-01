package com.example.savemaker.balance.models;

import java.util.Objects;

public class IncomeTypeBalance {
    private String typeName;
    private Double incomeTypeBalance;

    public IncomeTypeBalance() {}

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getIncomeTypeBalance() {
        return incomeTypeBalance;
    }

    public void setIncomeTypeBalance(Double incomeTypeBalance) {
        this.incomeTypeBalance = incomeTypeBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IncomeTypeBalance that = (IncomeTypeBalance) o;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeName);
    }
}
