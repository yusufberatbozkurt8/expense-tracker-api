package com.yusufberat.expensetracker.dto;

import com.yusufberat.expensetracker.model.ExpenseCategory;

import java.math.BigDecimal;
import java.util.Map;

public record MonthlyReportResponse(
        int year,
        int month,
        BigDecimal totalAmount,
        long expenseCount,
        Map<ExpenseCategory, BigDecimal> totalsByCategory
) {
}
