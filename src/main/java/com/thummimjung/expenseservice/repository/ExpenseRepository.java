package com.thummimjung.expenseservice.repository;

import com.thummimjung.expenseservice.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
