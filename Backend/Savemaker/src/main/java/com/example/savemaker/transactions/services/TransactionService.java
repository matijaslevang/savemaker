package com.example.savemaker.transactions.services;

import com.example.savemaker.balance.models.SpendingDetails;
import com.example.savemaker.transactions.dtos.CreateTransactionDTO;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.transactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction create(CreateTransactionDTO newTransaction, List<SpendingDetails> spendingDetails, Category category) {
        Transaction transaction = new Transaction();
        transaction.setDate(newTransaction.getDate());
        transaction.setSpendingDetails(spendingDetails);
        transaction.setNotes(newTransaction.getNotes());
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public Transaction get(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Transaction> getThreeMostRecent() {
        return transactionRepository.findTop3ByOrderByIdDesc();
    }

    public List<Transaction> getAll(Long categoryId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.getAll(categoryId, startDate, endDate);
    }
}
