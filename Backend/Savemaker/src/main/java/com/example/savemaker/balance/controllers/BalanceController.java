package com.example.savemaker.balance.controllers;

import com.example.savemaker.balance.models.IncomeTypeBalance;
import com.example.savemaker.balance.models.MainBalance;
import com.example.savemaker.balance.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    private final Long mainBalanceId = 1L;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getTotalBalance() {
        MainBalance balance = balanceService.getMainBalance(mainBalanceId);
        return new ResponseEntity<>(balance.getTotalBalance(), HttpStatus.OK);
    }

    @GetMapping(value = "/type/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getBalanceForCategory(@PathVariable Long categoryId) {
        IncomeTypeBalance incomeTypeBalance = balanceService.getIncomeTypeBalanceByCategory(categoryId);
        return new ResponseEntity<>(incomeTypeBalance.getBalance(), HttpStatus.OK);
    }

}
