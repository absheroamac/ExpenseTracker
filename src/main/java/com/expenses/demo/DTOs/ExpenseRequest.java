package com.expenses.demo.DTOs;

import com.expenses.demo.enums.Category;
import com.expenses.demo.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseRequest {

    private Long id;
    private String description;
    private double amount;
    private String category;
    private String type;
    private Long spaceId;
    private Long memeberId;
}
