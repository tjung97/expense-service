package com.thummimjung.expenseservice.controller;

import com.thummimjung.expenseservice.model.Expense;
import com.thummimjung.expenseservice.service.ExpenseService;
import com.thummimjung.expenseservice.service.MachineLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private MachineLearningService machineLearningService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            return ResponseEntity.ok(expense.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.createExpense(expense);
        Map<String, Object> prediction = machineLearningService.getPrediction(expense.getAmount());
        System.out.println("Created Expense: " + savedExpense.toString() + " with Prediction: " + prediction);;
        return savedExpense;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
