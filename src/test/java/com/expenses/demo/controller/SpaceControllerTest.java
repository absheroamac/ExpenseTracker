package com.expenses.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.expenses.demo.DTOs.AddMemberRequests;
import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.controllers.SpaceController;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.services.SpaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SpaceController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class SpaceControllerTest {

    @MockitoBean
    SpaceService spaceService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    // TODO: Create Space ( Success )

    @Test
    @WithMockUser(username = "testUser")
    public void createSpaceShouldReturnCreatedSpaceObject() throws JsonProcessingException, Exception {

        SpaceCreationRequest request = new SpaceCreationRequest("name", 0L, 50.00, 50.00, 50.00, 50.00);
        User user = new User(0L, "name", "email", "password", 50.00);
        Space response = new Space(0L, "name", user, 50.00, 50.00, 50.00, 50.00);

        when(spaceService.createSpace(any(SpaceCreationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/space/newspace").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpect(jsonPath("&.id").value(response.getId()));
    }

    // TODO: Create Space ( Failure )

    // TODO: Add Member to the Space ( Success )
    @Test
    @WithMockUser(username = "testUser")
    public void addMemberShouldReturnUpdatedSpaceObject() throws Exception {

        List<Long> memberIds = new ArrayList<>();
        memberIds.add(0L);
        AddMemberRequests request = new AddMemberRequests(memberIds);
        Space response = new Space(null, null, null, 50.00, 0, 0, 0);

        when(spaceService.addMembers(any(List.class))).thenReturn(response);

        mockMvc.perform(post("/space/addmember").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBudget").value(response.getTotalBudget()));

    }

    // TODO: Add Member to the Space ( Failure )

    // TODO: Add Members to the Space ( Success )
    @Test
    @WithMockUser(username = "testUser")
    public void addMembersShouldReturnUpdatedSpaceObject() {

    }

    // TODO: Add Members to the Space ( Success )
}
