package com.example.budgetapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "expense_categories")
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long userId; // Hər istifadəçi üçün fərqli kateqoriyalar

    @Column(nullable = false)
    private String type = "EXPENSE"; // Xərc kateqoriyası üçün
}
