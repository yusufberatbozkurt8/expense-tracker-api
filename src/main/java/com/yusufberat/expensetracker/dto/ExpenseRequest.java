package com.yusufberat.expensetracker.dto;

import com.yusufberat.expensetracker.model.ExpenseCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotBlank String title,
        String description,
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotNull ExpenseCategory category,
        @NotNull LocalDate expenseDate,
        @NotBlank String department
) {
}
