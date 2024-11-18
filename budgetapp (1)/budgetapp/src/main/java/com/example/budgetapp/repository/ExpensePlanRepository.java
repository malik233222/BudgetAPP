package com.example.budgetapp.repository;

import com.example.budgetapp.entity.ExpensePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensePlanRepository extends JpaRepository<ExpensePlan, Long> {
    List<ExpensePlan> findByUserId(Long userId);
}
