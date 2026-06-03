package com.yusufberat.expensetracker.config;

import com.yusufberat.expensetracker.model.Expense;
import com.yusufberat.expensetracker.model.ExpenseCategory;
import com.yusufberat.expensetracker.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
@Profile("dev")
public class DataLoader {

    @Bean
    CommandLineRunner seedExpenses(ExpenseRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }
            repository.save(sample("Ofis kırtasiye", "Kalem, kağıt", "120.50", ExpenseCategory.SUPPLIES, "Muhasebe"));
            repository.save(sample("Samsun-Ankara seyahat", "Personel ziyareti", "2450.00", ExpenseCategory.TRAVEL, "İnsan Kaynakları"));
            repository.save(sample("Elektrik faturası", "Mart dönemi", "890.25", ExpenseCategory.UTILITIES, "Muhasebe"));
        };
    }

    private Expense sample(String title, String desc, String amount, ExpenseCategory category, String dept) {
        Expense expense = new Expense();
        expense.setTitle(title);
        expense.setDescription(desc);
        expense.setAmount(new BigDecimal(amount));
        expense.setCategory(category);
        expense.setExpenseDate(LocalDate.now().minusDays(3));
        expense.setDepartment(dept);
        return expense;
    }
}
