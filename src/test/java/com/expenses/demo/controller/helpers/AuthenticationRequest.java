package com.expenses.demo.controller.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

    private Long id;
    private String name;
    private String email;
    private String password;
    private double income;

}
