package com.example.budgetapp.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseRequestDto {
    private Double amount;        // Məbləğ
    private String description;   // Təsvir
    private Long categoryId;      // Kateqoriya ID-si
    private LocalDateTime date;   // Xərc tarixi
}
