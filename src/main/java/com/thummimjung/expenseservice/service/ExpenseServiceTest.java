package com.thummimjung.expenseservice.service;

import com.thummimjung.expenseservice.model.Expense;
import com.thummimjung.expenseservice.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void testGetAllExpenses() {
        Expense expense1 = new Expense(1L, "Food", 10.0, LocalDate.now(), "Lunch");
        Expense expense2 = new Expense(2L, "Transport", 5.0, LocalDate.now(), "Bus fare");
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(expense1, expense2));

        List<Expense> expenses = expenseService.getAllExpenses();
        assertEquals(2, expenses.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testGetExpenseById() {
        Expense expense = new Expense(1L, "Food", 10.0, LocalDate.now(), "Lunch");
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Optional<Expense> foundExpense = expenseService.getExpenseById(1L);
        assertTrue(foundExpense.isPresent());
        assertEquals(expense, foundExpense.get());
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateExpense() {
        Expense expense = new Expense(1L, "Food", 10.0, LocalDate.now(), "Lunch");
        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense createdExpense = expenseService.createExpense(expense);
        assertEquals(expense, createdExpense);
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testDeleteExpense() {
        doNothing().when(expenseRepository).deleteById(1L);

        expenseService.deleteExpense(1L);
        verify(expenseRepository, times(1)).deleteById(1L);
    }
}
