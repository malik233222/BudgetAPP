package com.example.budgetapp.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpensePlanResponseDto {
    private Long id;
    private Double plannedAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; // Planın cari vəziyyəti
}
