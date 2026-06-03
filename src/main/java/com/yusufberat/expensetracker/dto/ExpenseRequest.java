package com.yusufberat.expensetracker.dto;

import com.yusufberat.expensetracker.model.ExpenseCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        @NotBlank @Size(max = 120) String title,
        @Size(max = 500) String description,
        @NotNull
        @DecimalMin("0.01")
        @Digits(integer = 12, fraction = 2)
        BigDecimal amount,
        @NotNull ExpenseCategory category,
        @NotNull @PastOrPresent LocalDate expenseDate,
        @NotBlank @Size(max = 80) String department
) {
}
