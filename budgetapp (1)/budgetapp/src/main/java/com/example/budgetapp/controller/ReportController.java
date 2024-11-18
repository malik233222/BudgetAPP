package com.example.budgetapp.controller;

import com.example.budgetapp.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // İki tarix aralığında gəlir və xərcləri qaytarır
    @GetMapping("/income-and-expense")
    public ResponseEntity<String> getIncomeAndExpense(
            @RequestParam Long userId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Double totalIncome = reportService.getTotalIncome(userId, startDate, endDate);
        Double totalExpense = reportService.getTotalExpense(userId, startDate, endDate);
        String response = String.format("Total Income: %.2f, Total Expense: %.2f", totalIncome, totalExpense);
        return ResponseEntity.ok(response);
    }

    // Cari balansı qaytarır
    @GetMapping("/current-balance")
    public ResponseEntity<Double> getCurrentBalance(@RequestParam Long userId) {
        return ResponseEntity.ok(reportService.getCurrentBalance(userId));
    }

    // Verilmiş tarixdə günün sonundakı balansı qaytarır
    @GetMapping("/balance-at-date")
    public ResponseEntity<Double> getBalanceAtDate(
            @RequestParam Long userId,
            @RequestParam LocalDateTime date) {
        return ResponseEntity.ok(reportService.getBalanceAtDate(userId, date));
    }
}
