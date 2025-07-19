package com.example.savemaker.balance.services;

import com.example.savemaker.balance.models.IncomeTypeBalance;
import com.example.savemaker.balance.models.MainBalance;
import com.example.savemaker.balance.repositories.IncomeTypeBalanceRepository;
import com.example.savemaker.balance.repositories.MainBalanceRepository;
import com.example.savemaker.transactions.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private MainBalanceRepository mainBalanceRepository;

    @Autowired
    private IncomeTypeBalanceRepository incomeTypeBalanceRepository;

    public MainBalance getMainBalance(Long id) {
        return mainBalanceRepository.getReferenceById(id);
    }

    public MainBalance applyTransaction(MainBalance mainBalance, Transaction transaction) {
        if (transaction.getCategory().getUsedForIncome()) {
            mainBalance.setTotalBalance(mainBalance.getTotalBalance() + transaction.getAmount());
            IncomeTypeBalance incomeTypeBalance = mainBalance.getIndividualBalances().stream().filter(v -> v.getIncomeCategory().getId() == transaction.getCategory().getId()).findFirst().orElse(null);
            if (incomeTypeBalance != null) {
                incomeTypeBalance.setBalance(incomeTypeBalance.getBalance() + transaction.getAmount());
                incomeTypeBalanceRepository.save(incomeTypeBalance);
            }
        } else {
            if (mainBalance.getTotalBalance() < transaction.getAmount()) {
                return null;
            }

            mainBalance.setTotalBalance(mainBalance.getTotalBalance() - transaction.getAmount());
            IncomeTypeBalance incomeTypeBalance = mainBalance.getIndividualBalances().stream().filter(v -> v.getIncomeCategory().getId() == transaction.getCategory().getId()).findFirst().orElse(null);
            if (incomeTypeBalance != null && incomeTypeBalance.getBalance() >= transaction.getAmount()) {
                incomeTypeBalance.setBalance(incomeTypeBalance.getBalance() - transaction.getAmount());
                incomeTypeBalanceRepository.save(incomeTypeBalance);
            }
            // TODO: if there is not enough funds on the given IncomeTypeBalance, go through global list
        }
        return mainBalanceRepository.save(mainBalance);
    }

    public IncomeTypeBalance getIncomeTypeBalance(Long id) {
        return incomeTypeBalanceRepository.getReferenceById(id);
    }

    public IncomeTypeBalance getIncomeTypeBalanceByCategory(@PathVariable Long categoryId) {
        return incomeTypeBalanceRepository.findByIncomeCategory(categoryId);
    }

    public List<IncomeTypeBalance> getAllIncomeTypeBalances() {
        return incomeTypeBalanceRepository.findAll();
    }

}
