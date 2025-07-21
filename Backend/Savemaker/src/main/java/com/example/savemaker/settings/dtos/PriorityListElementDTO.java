package com.example.savemaker.settings.dtos;

import com.example.savemaker.balance.models.IncomeTypeBalance;

public class PriorityListElementDTO {
    private Long incomeTypeBalanceId;
    private String categoryName;
    private Integer priority;

    public PriorityListElementDTO() {}

    public PriorityListElementDTO(IncomeTypeBalance incomeTypeBalance) {
        this.incomeTypeBalanceId = incomeTypeBalance.getId();
        this.categoryName = incomeTypeBalance.getIncomeCategory().getName();
        this.priority = incomeTypeBalance.getPriority();
    }

    public Long getIncomeTypeBalanceId() {
        return incomeTypeBalanceId;
    }

    public void setIncomeTypeBalanceId(Long incomeTypeBalanceId) {
        this.incomeTypeBalanceId = incomeTypeBalanceId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
