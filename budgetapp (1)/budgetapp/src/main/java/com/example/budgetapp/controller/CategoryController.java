package com.example.budgetapp.controller;

import com.example.budgetapp.dto.request.CategoryRequestDto;
import com.example.budgetapp.dto.response.CategoryResponseDto;
import com.example.budgetapp.exceptionHandler.OurException;
import com.example.budgetapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Xərc Kateqoriyaları
    @GetMapping("/expenses")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CategoryResponseDto>> getExpenseCategories(@RequestParam Long userId) {
        validateUserId(userId);
        return ResponseEntity.ok(categoryService.getAllExpenseCategories(userId));
    }

    @PostMapping("/expenses")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CategoryResponseDto> createExpenseCategory(@RequestBody CategoryRequestDto request,
                                                                     @RequestParam Long userId) {
        validateUserId(userId);
        return ResponseEntity.ok(categoryService.createExpenseCategory(request, userId));
    }

    @DeleteMapping("/expenses/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable Long id, @RequestParam Long userId) {
        validateUserId(userId);
        categoryService.deleteExpenseCategory(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Gəlir Kateqoriyaları
    @GetMapping("/incomes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CategoryResponseDto>> getIncomeCategories(@RequestParam Long userId) {
        validateUserId(userId);
        return ResponseEntity.ok(categoryService.getAllIncomeCategories(userId));
    }

    @PostMapping("/incomes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CategoryResponseDto> createIncomeCategory(@RequestBody CategoryRequestDto request,
                                                                    @RequestParam Long userId) {
        validateUserId(userId);
        return ResponseEntity.ok(categoryService.createIncomeCategory(request, userId));
    }

    @DeleteMapping("/incomes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteIncomeCategory(@PathVariable Long id, @RequestParam Long userId) {
        validateUserId(userId);
        categoryService.deleteIncomeCategory(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Helper Method: Validate User ID
    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new OurException("Invalid user ID provided");
        }
    }

}
