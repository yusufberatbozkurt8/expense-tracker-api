package com.yusufberat.expensetracker;

import com.yusufberat.expensetracker.dto.ExpenseRequest;
import com.yusufberat.expensetracker.dto.MonthlyReportResponse;
import com.yusufberat.expensetracker.model.ExpenseCategory;
import com.yusufberat.expensetracker.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ExpenseServiceTest {

    @Autowired
    private ExpenseService expenseService;

    @Test
    void createsExpenseAndBuildsMonthlyReport() {
        LocalDate today = LocalDate.now();
        expenseService.create(new ExpenseRequest(
                "Test masraf",
                "Demo",
                new BigDecimal("100.00"),
                ExpenseCategory.OFFICE,
                today,
                "IT"
        ));

        MonthlyReportResponse report = expenseService.monthlyReport(today.getYear(), today.getMonthValue());
        assertThat(report.expenseCount()).isGreaterThanOrEqualTo(1);
        assertThat(report.totalAmount()).isGreaterThanOrEqualTo(new BigDecimal("100.00"));
    }
}
