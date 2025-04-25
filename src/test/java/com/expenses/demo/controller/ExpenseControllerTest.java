package com.expenses.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.expenses.demo.DTOs.ExpenseRequest;
import com.expenses.demo.controllers.ExpenseController;
import com.expenses.demo.entities.Space;
import com.expenses.demo.services.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExpenseController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class ExpenseControllerTest {

    @MockitoBean
    ExpenseService expenseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    // TODO: AddExpense should return the updated Space
    // TODO: In second version it should return list of 10 recent transactions
    // including the last one and updated space.
    @Test
    @WithMockUser(username = "testUser")
    public void AddExpenseShouldReturnTheUpdatedSpace() throws Exception {

        ExpenseRequest request = new ExpenseRequest(0L, "food", 20.00, "FOOD", "SHARED", 0L, 0L);

        Space response = new Space(0L, "name", null, 0, 0, 0, 0);

        when(expenseService.AddExpense(any(ExpenseRequest.class))).thenReturn(response);

        mockMvc.perform(post("expense/addExpense").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpect(jsonPath("$.totalExpense").value(response.getTotalExpense()));

    }

    // TODO: RemoveExpense Expense Should return the updated Space
    @Test
    @WithMockUser(username = "testUser")
    public void RemoveExpenseShouldReturnTheUpdatedSpace() throws Exception {

        Space response = new Space(0L, "name", null, 0, 0, 0, 0);

        when(expenseService.RemoveExpense(anyString())).thenReturn(response);

        mockMvc.perform(post("expense/removeExpense").contentType(MediaType.APPLICATION_JSON)
                .content("id")).andExpect(status().isOk())
                .andExpect(jsonPath("$.totalExpense").value(response.getTotalExpense()));

    }

    // TODO: UpdateExpense Should return the updated Space
    @Test
    @WithMockUser(username = "testUser")
    public void UpdateExpenseShouldReturnTheUpdatedSpace() {

    }

}
