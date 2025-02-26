package com.expenses.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.expenses.demo.DTOs.AuthReques;
import com.expenses.demo.controllers.AuthenticationController;
import com.expenses.demo.entities.User;
import com.expenses.demo.services.UserService;
import com.expenses.demo.util.JwtUtility;
import com.expenses.demo.util.SecureUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.core.Authentication;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AuthenticationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    // Mocks for required dependencies in your controller (or filters)
    @MockitoBean
    private JwtUtility jwtUtility;
    
    @MockitoBean
    private AuthenticationManager authenticationManager;
    
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private SecureUserService secureUserService;

    @Test
    @WithMockUser(username = "testUser")
    public void registerUserShouldGiveUserObjectAndOkStatus() throws Exception {
        User user = new User(1L, "name", "userName@gmail.com", "password", 5000.0);
        when(userService.registerUser(any(User.class))).thenReturn(user);

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void loginUserShouldGiveTokenObjectAndOkStatus() throws Exception {

        AuthReques authRequest = new AuthReques("username","password");

        Authentication dummyAuthentication = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(dummyAuthentication);

        when(jwtUtility.generateToken(anyString())).thenReturn("token");

        mockMvc.perform(post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(jsonPath("$.token").value("token")).andExpect(status().isOk());


    }


    // TODO: Write Tests for failure Login
    
    
    // TODO: Write Failure Test for
}
