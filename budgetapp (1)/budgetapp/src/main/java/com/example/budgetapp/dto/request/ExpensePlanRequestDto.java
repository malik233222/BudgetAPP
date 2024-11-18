package com.example.budgetapp.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpensePlanRequestDto {
    private Double plannedAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
