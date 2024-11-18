package com.example.budgetapp.repository;

import com.example.budgetapp.entity.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
    List<IncomeCategory> findByUserId(Long userId);
}
