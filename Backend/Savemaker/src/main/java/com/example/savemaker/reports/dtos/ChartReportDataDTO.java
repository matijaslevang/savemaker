package com.example.savemaker.reports.dtos;

import com.example.savemaker.transactions.dtos.CategoryDTO;

import java.util.List;

public class ChartReportDataDTO {

    private List<CategoryDTO> categories;
    private List<Double> amounts;

    public ChartReportDataDTO() {}

    public ChartReportDataDTO(List<CategoryDTO> categories, List<Double> amounts) {
        this.categories = categories;
        this.amounts = amounts;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }
}
