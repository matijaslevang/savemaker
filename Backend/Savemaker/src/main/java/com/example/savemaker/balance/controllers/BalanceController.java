package com.example.savemaker.balance.controllers;

import com.example.savemaker.balance.dtos.IncomeTypeBalanceDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomeTypeBalanceDTO>> getAllIncomeTypeBalances() {
        List<IncomeTypeBalance> balances = balanceService.getAllIncomeTypeBalances();
        List<IncomeTypeBalanceDTO> dtos = balances.stream().map(x -> new IncomeTypeBalanceDTO(x)).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
