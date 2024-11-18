package com.example.budgetapp.controller;

import com.example.budgetapp.dto.request.ExpensePlanRequestDto;
import com.example.budgetapp.dto.response.ExpensePlanResponseDto;
import com.example.budgetapp.service.ExpensePlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-plans")
public class ExpensePlanController {

    private final ExpensePlanService planService;

    public ExpensePlanController(ExpensePlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<ExpensePlanResponseDto> createPlan(@RequestBody ExpensePlanRequestDto request,
                                                             @RequestParam Long userId) {
        return ResponseEntity.ok(planService.createExpensePlan(request, userId));
    }

    @GetMapping
    public ResponseEntity<List<ExpensePlanResponseDto>> getAllPlans(@RequestParam Long userId) {
        return ResponseEntity.ok(planService.getAllPlans(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpensePlanResponseDto> checkPlan(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(planService.checkPlan(id, userId));
    }
}
