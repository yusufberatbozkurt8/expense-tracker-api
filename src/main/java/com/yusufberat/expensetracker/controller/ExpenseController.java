package com.yusufberat.expensetracker.controller;

import com.yusufberat.expensetracker.dto.ExpenseRequest;
import com.yusufberat.expensetracker.dto.ExpenseResponse;
import com.yusufberat.expensetracker.dto.MonthlyReportResponse;
import com.yusufberat.expensetracker.model.ExpenseCategory;
import com.yusufberat.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse create(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.create(request);
    }

    @GetMapping
    public List<ExpenseResponse> list(@RequestParam(required = false) ExpenseCategory category) {
        if (category != null) {
            return expenseService.listByCategory(category);
        }
        return expenseService.listAll();
    }

    @GetMapping("/{id}")
    public ExpenseResponse get(@PathVariable Long id) {
        return expenseService.getById(id);
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        return expenseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        expenseService.delete(id);
    }

    @GetMapping("/reports/monthly")
    public MonthlyReportResponse monthlyReport(
            @RequestParam int year,
            @RequestParam int month) {
        return expenseService.monthlyReport(year, month);
    }
}
