package com.example.savemaker.balance.services;

import com.example.savemaker.balance.models.IncomeTypeBalance;
import com.example.savemaker.balance.models.MainBalance;
import com.example.savemaker.balance.models.SpendingDetails;
import com.example.savemaker.balance.repositories.IncomeTypeBalanceRepository;
import com.example.savemaker.balance.repositories.MainBalanceRepository;
import com.example.savemaker.balance.repositories.SpendingDetailsRepository;
import com.example.savemaker.settings.dtos.PriorityListElementDTO;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BalanceService {

    @Autowired
    private MainBalanceRepository mainBalanceRepository;

    @Autowired
    private IncomeTypeBalanceRepository incomeTypeBalanceRepository;
    @Autowired
    private SpendingDetailsRepository spendingDetailsRepository;

    public MainBalance getMainBalance(Long id) {
        return mainBalanceRepository.getReferenceById(id);
    }

    public List<SpendingDetails> applyTransaction(MainBalance mainBalance, Double amount, Category category) {
        List<SpendingDetails> spendingDetailsList = new ArrayList<>();
        if (category.getUsedForIncome()) {
            mainBalance.setTotalBalance(mainBalance.getTotalBalance() + amount);
            IncomeTypeBalance incomeTypeBalance = mainBalance.getIndividualBalances().stream().filter(v -> v.getIncomeCategory().getId() == category.getId()).findFirst().orElse(null);
            if (incomeTypeBalance != null) {
                incomeTypeBalance.setBalance(incomeTypeBalance.getBalance() + amount);
                incomeTypeBalanceRepository.save(incomeTypeBalance);
                spendingDetailsList.add(this.createSpendingDetails(category, amount));
            }
        } else {
            if (mainBalance.getTotalBalance() < amount) {
                return null;
            }

            mainBalance.setTotalBalance(mainBalance.getTotalBalance() - amount);
            IncomeTypeBalance incomeTypeBalance = mainBalance.getIndividualBalances().stream().filter(v -> Objects.equals(v.getIncomeCategory().getId(), category.getPreferredSpendingCategory().getId())).findFirst().orElse(null);
            if (incomeTypeBalance != null) {
                if (incomeTypeBalance.getBalance() >= amount) {
                    incomeTypeBalance.setBalance(incomeTypeBalance.getBalance() - amount);
                    incomeTypeBalanceRepository.save(incomeTypeBalance);
                    spendingDetailsList.add(this.createSpendingDetails(category, amount));
                } else {
                    amount -= incomeTypeBalance.getBalance();
                    spendingDetailsList.add(this.createSpendingDetails(category, incomeTypeBalance.getBalance()));
                    incomeTypeBalance.setBalance(0.0);
                    incomeTypeBalanceRepository.save(incomeTypeBalance);

                    List<IncomeTypeBalance> priorityList = getPriorityList();
                    for (IncomeTypeBalance priority : priorityList) {
                        if (priority.getBalance() == 0.0) { continue; }

                        if (priority.getBalance() < amount) {
                            amount -= priority.getBalance();
                            spendingDetailsList.add(this.createSpendingDetails(priority.getIncomeCategory(), priority.getBalance()));
                            priority.setBalance(0.0);
                            incomeTypeBalanceRepository.save(priority);
                        } else {
                            priority.setBalance(priority.getBalance() - amount);
                            spendingDetailsList.add(this.createSpendingDetails(priority.getIncomeCategory(), amount));
                            incomeTypeBalanceRepository.save(priority);
                            break;
                        }
                    }
                }
            }
        }
        mainBalanceRepository.save(mainBalance);
        return spendingDetailsList;
    }

    public IncomeTypeBalance getIncomeTypeBalance(Long id) {
        return incomeTypeBalanceRepository.findById(id).orElse(null);
    }

    public IncomeTypeBalance getIncomeTypeBalanceByCategory(@PathVariable Long categoryId) {
        return incomeTypeBalanceRepository.findByIncomeCategory(categoryId);
    }

    public List<IncomeTypeBalance> getAllIncomeTypeBalances() {
        return incomeTypeBalanceRepository.findAll();
    }

    public boolean updatePriorityList(List<PriorityListElementDTO> updatedList) {
        for (PriorityListElementDTO priorityListElementDTO : updatedList) {
            IncomeTypeBalance balance = this.getIncomeTypeBalance(priorityListElementDTO.getIncomeTypeBalanceId());
            balance.setPriority(priorityListElementDTO.getPriority());
            incomeTypeBalanceRepository.save(balance);
        }
        return true;
    }

    public SpendingDetails createSpendingDetails(Category category, Double amount) {
        SpendingDetails spendingDetails = new SpendingDetails();
        spendingDetails.setCategory(category);
        spendingDetails.setAmount(amount);
        return spendingDetailsRepository.save(spendingDetails);
    }

    public List<IncomeTypeBalance> getPriorityList() {
        return incomeTypeBalanceRepository.findAllOrderByPriority();
    }

}
