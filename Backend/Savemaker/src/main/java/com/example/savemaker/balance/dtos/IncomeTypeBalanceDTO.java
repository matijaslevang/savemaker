package com.example.savemaker.balance.dtos;

import com.example.savemaker.balance.models.IncomeTypeBalance;

public class IncomeTypeBalanceDTO {
    private String typeName;
    private Double incomeTypeBalance;

    public IncomeTypeBalanceDTO() {}

    public IncomeTypeBalanceDTO(IncomeTypeBalance incomeTypeBalance) {
        this.incomeTypeBalance = incomeTypeBalance.getBalance();
        this.typeName = incomeTypeBalance.getIncomeCategory().getName();
    }

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
