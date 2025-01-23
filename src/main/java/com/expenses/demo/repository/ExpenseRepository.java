package com.expenses.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.demo.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    
}
