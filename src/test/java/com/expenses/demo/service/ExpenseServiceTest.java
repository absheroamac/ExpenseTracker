package com.expenses.demo.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.demo.DTOs.ExpenseRequest;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.repository.ExpenseRepository;
import com.expenses.demo.services.ExpenseService;
import com.expenses.demo.services.SpaceService;
import com.expenses.demo.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceTest {

    @Mock
    ExpenseRepository repository;

    @Mock
    SpaceService spaceService;

    @Mock
    UserService userService;

    @InjectMocks
    ExpenseService service;

    @Test
    public void AddExpenseShouldReturnTheUpdatedSpace() {

        ExpenseRequest request = new ExpenseRequest(1L, "Food", 25, "Food", "somethign", 1L, 1L);
        User user = new User(0L, "name", "email", "password", 2500);
        Space space = new Space(1L, "name", null, 0, 0, 0, 0);

        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(spaceService.getSpaceById(anyString())).thenReturn(Optional.of(space));

    }

}
