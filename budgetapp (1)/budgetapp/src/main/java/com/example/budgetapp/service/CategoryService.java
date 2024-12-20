package com.example.budgetapp.service;

import com.example.budgetapp.dto.request.CategoryRequestDto;
import com.example.budgetapp.dto.response.CategoryResponseDto;
import com.example.budgetapp.entity.ExpenseCategory;
import com.example.budgetapp.entity.IncomeCategory;
import com.example.budgetapp.repository.ExpenseCategoryRepository;
import com.example.budgetapp.repository.IncomeCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final ExpenseCategoryRepository expenseRepo;
    private final IncomeCategoryRepository incomeRepo;

    public CategoryService(ExpenseCategoryRepository expenseRepo, IncomeCategoryRepository incomeRepo) {
        this.expenseRepo = expenseRepo;
        this.incomeRepo = incomeRepo;
    }

    // Expense Categories
    public List<CategoryResponseDto> getAllExpenseCategories(Long userId) {
        return expenseRepo.findByUserId(userId).stream()
                .map(this::mapExpenseToDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto createExpenseCategory(CategoryRequestDto request, Long userId) {
        ExpenseCategory category = new ExpenseCategory();
        category.setName(request.getName());
        category.setUserId(userId);
        category.setType("EXPENSE");
        return mapExpenseToDto(expenseRepo.save(category));
    }

    public void deleteExpenseCategory(Long id, Long userId) {
        ExpenseCategory category = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense category not found"));
        if (!category.getUserId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }
        expenseRepo.delete(category);
    }

    // Income Categories
    public List<CategoryResponseDto> getAllIncomeCategories(Long userId) {
        return incomeRepo.findByUserId(userId).stream()
                .map(this::mapIncomeToDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto createIncomeCategory(CategoryRequestDto request, Long userId) {
        IncomeCategory category = new IncomeCategory();
        category.setName(request.getName());
        category.setUserId(userId);
        category.setType("INCOME");
        return mapIncomeToDto(incomeRepo.save(category));
    }

    public void deleteIncomeCategory(Long id, Long userId) {
        IncomeCategory category = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income category not found"));
        if (!category.getUserId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }
        incomeRepo.delete(category);
    }

    // Helper: Map Expense Category to DTO
    private CategoryResponseDto mapExpenseToDto(ExpenseCategory category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    // Helper: Map Income Category to DTO
    private CategoryResponseDto mapIncomeToDto(IncomeCategory category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
