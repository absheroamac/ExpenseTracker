package com.expenses.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.demo.DTOs.ExpenseRequest;
import com.expenses.demo.DTOs.RemoveExpenseRequest;
import com.expenses.demo.entities.Space;
import com.expenses.demo.services.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    ExpenseService service;

    public ExpenseController(ExpenseService expenseService) {
        this.service = expenseService;

    }

    public ResponseEntity<Space> addExpense(@RequestBody ExpenseRequest request) {

        try {
            Space space = service.AddExpense(request);
            return ResponseEntity.ok(space);

        } catch (Exception err) {
            throw new RuntimeException("Something went wrong");
        }

    }

    public ResponseEntity<Space> removeExpense(@RequestBody RemoveExpenseRequest request) {

        try {
            Space space = service.RemoveExpense(request);
            return ResponseEntity.ok(space);
        } catch (Exception err) {
            throw new RuntimeException("Something went wront");
        }
    }

}
