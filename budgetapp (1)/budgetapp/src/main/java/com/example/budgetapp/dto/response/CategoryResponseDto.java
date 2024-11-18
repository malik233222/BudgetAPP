package com.example.budgetapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Add this annotation
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
}
