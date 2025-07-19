package com.example.savemaker.balance.models;

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
}
