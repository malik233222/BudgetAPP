package com.example.budgetapp.service;

import com.example.budgetapp.repository.ExpenseRepository;
import com.example.budgetapp.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public ReportService(IncomeRepository incomeRepository, ExpenseRepository expenseRepository) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
    }

    // İki tarix aralığında gəlir və xərcləri qaytarır
    public Double getTotalIncome(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
                .stream()
                .mapToDouble(income -> income.getAmount())
                .sum();
    }

    public Double getTotalExpense(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
                .stream()
                .mapToDouble(expense -> expense.getAmount())
                .sum();
    }

    // Cari balansı qaytarır
    public Double getCurrentBalance(Long userId) {
        Double totalIncome = getTotalIncome(userId, LocalDateTime.MIN, LocalDateTime.now());
        Double totalExpense = getTotalExpense(userId, LocalDateTime.MIN, LocalDateTime.now());
        return totalIncome - totalExpense;
    }

    // Verilmiş tarixdə günün sonundakı balansı qaytarır
    public Double getBalanceAtDate(Long userId, LocalDateTime date) {
        Double totalIncome = getTotalIncome(userId, LocalDateTime.MIN, date);
        Double totalExpense = getTotalExpense(userId, LocalDateTime.MIN, date);
        return totalIncome - totalExpense;
    }
}
