package com.example.budgetapp.repository;

import com.example.budgetapp.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserIdAndCategoryIdAndDateBetween(Long userId, Long categoryId, LocalDateTime startDate, LocalDateTime endDate);
    List<Income> findByUserIdAndDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
