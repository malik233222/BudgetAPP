package com.example.budgetapp.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseResponseDto {
    private Long id;              // Xərc ID-si
    private Double amount;        // Məbləğ
    private String description;   // Təsvir
    private Long categoryId;      // Kateqoriya ID-si
    private LocalDateTime date;   // Xərc tarixi
}
