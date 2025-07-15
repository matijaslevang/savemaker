package com.example.savemaker.balance.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "main_balances")
public class MainBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalBalance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "income_type_balance_fk_table",
               joinColumns = @JoinColumn(name = "main_balance_id"),
               inverseJoinColumns = @JoinColumn(name = "income_type_balance_id"))
    private List<IncomeTypeBalance> individualBalances;

    public MainBalance() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public List<IncomeTypeBalance> getIndividualBalances() {
        return individualBalances;
    }

    public void setIndividualBalances(List<IncomeTypeBalance> individualBalances) {
        this.individualBalances = individualBalances;
    }
}
