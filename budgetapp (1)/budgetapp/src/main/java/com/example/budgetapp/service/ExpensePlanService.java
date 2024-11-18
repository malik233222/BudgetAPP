package com.example.budgetapp.service;

import com.example.budgetapp.dto.request.ExpensePlanRequestDto;
import com.example.budgetapp.dto.response.ExpensePlanResponseDto;
import com.example.budgetapp.entity.Expense;
import com.example.budgetapp.entity.ExpensePlan;
import com.example.budgetapp.repository.ExpensePlanRepository;
import com.example.budgetapp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpensePlanService {

    private final ExpensePlanRepository planRepository;
    private final ExpenseRepository expenseRepository;

    public ExpensePlanService(ExpensePlanRepository planRepository, ExpenseRepository expenseRepository) {
        this.planRepository = planRepository;
        this.expenseRepository = expenseRepository;
    }

    public ExpensePlanResponseDto createExpensePlan(ExpensePlanRequestDto request, Long userId) {
        ExpensePlan plan = new ExpensePlan();
        plan.setUserId(userId);
        plan.setPlannedAmount(request.getPlannedAmount());
        plan.setStartDate(request.getStartDate());
        plan.setEndDate(request.getEndDate());
        return mapToDto(planRepository.save(plan));
    }

    public List<ExpensePlanResponseDto> getAllPlans(Long userId) {
        return planRepository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public ExpensePlanResponseDto checkPlan(Long planId, Long userId) {
        ExpensePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        if (!plan.getUserId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }

        // Hesablama
        double totalExpenses = expenseRepository.findByUserIdAndDateBetween(
                        userId, plan.getStartDate(), plan.getEndDate())
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        long daysElapsed = Math.max(1, plan.getStartDate().until(LocalDateTime.now(), java.time.temporal.ChronoUnit.DAYS));
        long totalDays = Math.max(1, plan.getStartDate().until(plan.getEndDate(), java.time.temporal.ChronoUnit.DAYS));
        double expectedSpending = (plan.getPlannedAmount() / totalDays) * daysElapsed;

        // Plan vəziyyəti
        if (totalExpenses > expectedSpending) {
            plan.setStatus("EXCEEDED");
        } else if (totalExpenses < plan.getPlannedAmount()) {
            plan.setStatus("ACTIVE");
        } else {
            plan.setStatus("COMPLETED");
        }

        planRepository.save(plan);

        return mapToDto(plan);
    }

    private ExpensePlanResponseDto mapToDto(ExpensePlan plan) {
        ExpensePlanResponseDto dto = new ExpensePlanResponseDto();
        dto.setId(plan.getId());
        dto.setPlannedAmount(plan.getPlannedAmount());
        dto.setStartDate(plan.getStartDate());
        dto.setEndDate(plan.getEndDate());
        dto.setStatus(plan.getStatus());
        return dto;
    }
}
