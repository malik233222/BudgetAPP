package com.example.budgetapp.controller;

import com.example.budgetapp.dto.request.IncomeRequestDto;
import com.example.budgetapp.dto.response.IncomeResponseDto;
import com.example.budgetapp.service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public ResponseEntity<IncomeResponseDto> createIncome(@RequestBody IncomeRequestDto request, @RequestParam Long userId) {
        return ResponseEntity.ok(incomeService.createIncome(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<IncomeResponseDto>> getAllIncomes(@RequestParam Long userId) {
        return ResponseEntity.ok(incomeService.getAllIncomes(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id, @RequestParam Long userId) {
        incomeService.deleteIncome(id, userId);
        return ResponseEntity.noContent().build();
    }
}
