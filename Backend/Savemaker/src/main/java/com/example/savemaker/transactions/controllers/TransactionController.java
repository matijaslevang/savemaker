package com.example.savemaker.transactions.controllers;

import com.example.savemaker.balance.models.MainBalance;
import com.example.savemaker.balance.models.SpendingDetails;
import com.example.savemaker.balance.services.BalanceService;
import com.example.savemaker.transactions.dtos.CreateTransactionDTO;
import com.example.savemaker.transactions.dtos.TransactionDTO;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.Transaction;
import com.example.savemaker.transactions.services.CategoryService;
import com.example.savemaker.transactions.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDTO>> getRecent() {
        List<Transaction> recentThree = transactionService.getThreeMostRecent();
        List<TransactionDTO> dtos = recentThree.stream().map(TransactionDTO::new).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> create(@RequestBody CreateTransactionDTO transaction) {
        Category category = categoryService.getCategory(transaction.getCategoryId());
        List<SpendingDetails> spendingDetails = balanceService.applyTransaction(balanceService.getMainBalance(1L), transaction.getAmount(), category);
        if (spendingDetails == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Transaction createdTransaction = transactionService.create(transaction, spendingDetails, category);
        if (createdTransaction == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new TransactionDTO(createdTransaction), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDTO>> getAll(
            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate
    ) {
        if (startDate == null) {
            startDate = LocalDate.of(1970, 1, 1);
        } else {
            startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
        }

        if (endDate == null) {
            endDate = LocalDate.of(2099, 12, 31);
        } else {
            endDate = LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
        }

        List<Transaction> transactions = transactionService.getAll(categoryId, startDate, endDate);
        List<TransactionDTO> dtos = transactions.stream().map(TransactionDTO::new).toList();

        return new ResponseEntity<List<TransactionDTO>>(dtos, HttpStatus.OK);
    }

}
