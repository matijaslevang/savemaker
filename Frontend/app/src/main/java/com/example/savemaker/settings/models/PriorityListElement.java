package com.example.savemaker.settings.models;

public class PriorityListElement {
    private Long incomeTypeBalanceId;
    private String categoryName;
    private Integer priority;

    public PriorityListElement() {}

    public String getCategoryName() {
        return categoryName;
    }

    public Long getIncomeTypeBalanceId() {
        return incomeTypeBalanceId;
    }

    public void setIncomeTypeBalanceId(Long incomeTypeBalanceId) {
        this.incomeTypeBalanceId = incomeTypeBalanceId;
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
