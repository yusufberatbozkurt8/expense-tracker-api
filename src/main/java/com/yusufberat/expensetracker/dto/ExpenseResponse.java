package com.yusufberat.expensetracker.dto;

import com.yusufberat.expensetracker.model.Expense;
import com.yusufberat.expensetracker.model.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String title,
        String description,
        BigDecimal amount,
        ExpenseCategory category,
        LocalDate expenseDate,
        String department
) {
    public static ExpenseResponse from(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getExpenseDate(),
                expense.getDepartment()
        );
    }
}
