package com.example.budgetapp.repository;

import com.example.budgetapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserIdAndCategoryIdAndDateBetween(Long userId, Long categoryId, LocalDateTime startDate, LocalDateTime endDate);
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
