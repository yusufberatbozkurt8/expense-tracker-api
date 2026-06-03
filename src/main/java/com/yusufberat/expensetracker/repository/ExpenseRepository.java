package com.yusufberat.expensetracker.repository;

import com.yusufberat.expensetracker.model.Expense;
import com.yusufberat.expensetracker.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByExpenseDateBetween(LocalDate start, LocalDate end);

    List<Expense> findByCategory(ExpenseCategory category);

    @Query("""
            SELECT e.category, SUM(e.amount)
            FROM Expense e
            WHERE e.expenseDate BETWEEN :start AND :end
            GROUP BY e.category
            """)
    List<Object[]> sumByCategoryBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM Expense e
            WHERE e.expenseDate BETWEEN :start AND :end
            """)
    BigDecimal sumAmountBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
