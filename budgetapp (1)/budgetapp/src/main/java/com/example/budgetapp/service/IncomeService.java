package com.example.budgetapp.service;

import com.example.budgetapp.dto.request.IncomeRequestDto;
import com.example.budgetapp.dto.response.IncomeResponseDto;
import com.example.budgetapp.entity.Income;
import com.example.budgetapp.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public IncomeResponseDto createIncome(IncomeRequestDto request, Long userId) {
        Income income = new Income();
        income.setAmount(request.getAmount());
        income.setDescription(request.getDescription());
        income.setCategoryId(request.getCategoryId());
        income.setDate(request.getDate());
        income.setUserId(userId);
        return mapToDto(incomeRepository.save(income));
    }

    public List<IncomeResponseDto> getAllIncomes(Long userId) {
        return incomeRepository.findByUserIdAndDateBetween(userId, LocalDateTime.now().minusYears(1), LocalDateTime.now())
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteIncome(Long id, Long userId) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        if (!income.getUserId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }
        incomeRepository.delete(income);
    }

    private IncomeResponseDto mapToDto(Income income) {
        IncomeResponseDto dto = new IncomeResponseDto();
        dto.setId(income.getId());
        dto.setAmount(income.getAmount());
        dto.setDescription(income.getDescription());
        dto.setCategoryId(income.getCategoryId());
        dto.setDate(income.getDate());
        return dto;
    }
}
