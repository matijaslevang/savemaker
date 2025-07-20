package com.example.savemaker.reports.models;

import com.example.savemaker.transactions.models.Category;

import java.util.List;

public class ChartReportData {
    private List<Category> categories;
    private List<Double> amounts;

    public ChartReportData() {}

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Double> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<Double> amounts) {
        this.amounts = amounts;
    }
}
