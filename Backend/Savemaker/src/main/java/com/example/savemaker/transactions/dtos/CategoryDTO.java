package com.example.savemaker.transactions.dtos;

import com.example.savemaker.transactions.models.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {
    private Long id;
    private String name;
    @JsonProperty("isUsedForIncome")
    private Boolean isUsedForIncome;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.isUsedForIncome = category.getUsedForIncome();
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
