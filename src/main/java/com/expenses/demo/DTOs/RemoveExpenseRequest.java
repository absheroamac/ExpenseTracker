package com.expenses.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RemoveExpenseRequest {

    private Long spaceId;
    private Long expenseId;

}
