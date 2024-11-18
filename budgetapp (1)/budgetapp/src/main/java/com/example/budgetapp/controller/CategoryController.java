package com.example.budgetapp.controller;

import com.example.budgetapp.dto.request.CategoryRequestDto;
import com.example.budgetapp.dto.response.CategoryResponseDto;
import com.example.budgetapp.service.CategoryService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryResponseDto>> getExpenseCategories(@RequestParam Long userId) {
        return ResponseEntity.ok(categoryService.getAllExpenseCategories(userId));
    }

    @PostMapping("/expenses")
    public ResponseEntity<CategoryResponseDto> createExpenseCategory(@RequestBody CategoryRequestDto request,
                                                                     @RequestParam Long userId) {
        return ResponseEntity.ok(categoryService.createExpenseCategory(request, userId));
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable Long id, @RequestParam Long userId) {
        categoryService.deleteExpenseCategory(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Gəlir Kateqoriyaları
    @GetMapping("/incomes")
    public ResponseEntity<List<CategoryResponseDto>> getIncomeCategories(@RequestParam Long userId) {
        return ResponseEntity.ok(categoryService.getAllIncomeCategories(userId));
    }

    @PostMapping("/incomes")
    public ResponseEntity<CategoryResponseDto> createIncomeCategory(@RequestBody CategoryRequestDto request,
                                                                    @RequestParam Long userId) {
        return ResponseEntity.ok(categoryService.createIncomeCategory(request, userId));
    }

    @DeleteMapping("/incomes/{id}")
    public ResponseEntity<Void> deleteIncomeCategory(@PathVariable Long id, @RequestParam Long userId) {
        categoryService.deleteIncomeCategory(id, userId);
        return ResponseEntity.noContent().build();
    }
}
