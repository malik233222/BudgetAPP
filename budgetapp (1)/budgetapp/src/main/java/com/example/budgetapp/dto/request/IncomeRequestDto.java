package com.example.budgetapp.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncomeRequestDto {
    private Double amount;        // Məbləğ
    private String description;   // Təsvir
    private Long categoryId;      // Kateqoriya ID-si
    private LocalDateTime date;   // Gəlir tarixi
}
