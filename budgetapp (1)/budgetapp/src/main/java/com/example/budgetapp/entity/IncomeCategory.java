package com.example.budgetapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "income_categories")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long userId; // Hər istifadəçi üçün fərqli kateqoriyalar

    @Column(nullable = false)
    private String type = "INCOME"; // Gəlir kateqoriyası üçün
}
