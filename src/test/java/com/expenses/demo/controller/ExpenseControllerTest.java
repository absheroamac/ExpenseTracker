package com.expenses.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.expenses.demo.controllers.ExpenseController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExpenseController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class ExpenseControllerTest {

    // TODO: AddExpense should return the updated Space
    @Test
    @WithMockUser(username = "testUser")
    public void AddExpenseShouldReturnTheUpdatedSpace() {

    }

    // TODO: RemoveExpense Expense Should return the updated Space
    @Test
    @WithMockUser(username = "testUser")
    public void RemoveExpenseShouldReturnTheUpdatedSpace() {

    }

    // TODO: UpdateExpense Should return the updated Space
    @Test
    @WithMockUser(username = "testUser")
    public void UpdateExpenseShouldReturnTheUpdatedSpace() {

    }

}
