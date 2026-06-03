package com.yusufberat.expensetracker.service;

import com.yusufberat.expensetracker.dto.ExpenseRequest;
import com.yusufberat.expensetracker.dto.ExpenseResponse;
import com.yusufberat.expensetracker.dto.MonthlyReportResponse;
import com.yusufberat.expensetracker.model.Expense;
import com.yusufberat.expensetracker.model.ExpenseCategory;
import com.yusufberat.expensetracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponse create(ExpenseRequest request) {
        Expense expense = new Expense();
        mapToEntity(request, expense);
        return ExpenseResponse.from(expenseRepository.save(expense));
    }

    @Transactional(readOnly = true)
    public ExpenseResponse getById(Long id) {
        return ExpenseResponse.from(findOrThrow(id));
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> listAll() {
        return expenseRepository.findAll().stream().map(ExpenseResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> listByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category).stream().map(ExpenseResponse::from).toList();
    }

    public ExpenseResponse update(Long id, ExpenseRequest request) {
        Expense expense = findOrThrow(id);
        mapToEntity(request, expense);
        return ExpenseResponse.from(expense);
    }

    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Masraf kaydı bulunamadı: " + id);
        }
        expenseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public MonthlyReportResponse monthlyReport(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

        Map<ExpenseCategory, BigDecimal> totals = new EnumMap<>(ExpenseCategory.class);
        for (Object[] row : expenseRepository.sumByCategoryBetween(start, end)) {
            totals.put((ExpenseCategory) row[0], (BigDecimal) row[1]);
        }

        List<Expense> expenses = expenseRepository.findByExpenseDateBetween(start, end);
        BigDecimal total = expenseRepository.sumAmountBetween(start, end);

        return new MonthlyReportResponse(year, month, total, expenses.size(), totals);
    }

    private Expense findOrThrow(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Masraf kaydı bulunamadı: " + id));
    }

    private void mapToEntity(ExpenseRequest request, Expense expense) {
        expense.setTitle(request.title());
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setCategory(request.category());
        expense.setExpenseDate(request.expenseDate());
        expense.setDepartment(request.department());
    }
}
