package com.example.savemaker.reports.services;

import com.example.savemaker.reports.dtos.ChartReportDataDTO;
import com.example.savemaker.transactions.dtos.CategoryDTO;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.transactions.services.CategoryService;
import com.example.savemaker.transactions.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TransactionService transactionService;

    public ReportsService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ChartReportDataDTO getChartReportDataDTO(LocalDate startDate, LocalDate endDate) {
        List<Category> allCategories = categoryService.getAll();
        List<Double> amounts = new ArrayList<>();
        for (Category category : allCategories) {
            List<Transaction> temp = transactionService.getAll(category.getId(), startDate, endDate);
            Double sum = temp.stream().mapToDouble(Transaction::getTotalAmount).sum();
            amounts.add(sum);
        }
        return new ChartReportDataDTO(allCategories.stream().map(v -> new CategoryDTO(v)).toList(), amounts);
    }
}
