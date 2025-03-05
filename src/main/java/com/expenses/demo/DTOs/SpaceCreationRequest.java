package com.expenses.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceCreationRequest {

    private String name;
    private Long userID;
    private double totalBudget;
    private double livingBudget;
    private double livingExpense;
    private double totalExpense;
}
