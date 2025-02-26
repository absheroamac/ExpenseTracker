package com.expenses.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.expenses.demo.controllers.SpaceController;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SpaceController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class SpaceControllerTest {

    @Autowired
    MockMvc mockMvc;



    //TODO: Create Space ( Success )

    @Test
    @WithMockUser(username = "testUser")
    public void createSpaceShouldReturnCreatedSpaceObject(){

    }

    //TODO: Create Space ( Failure )

    //TODO: Add Member to the Space ( Success )
    @Test
    @WithMockUser(username = "testUser")
    public void addMemberShouldReturnUpdatedSpaceObject(){

    }

    //TODO: Add Member to the Space ( Failure )

    //TODO: Add Members to the Space ( Success )
    @Test
    @WithMockUser(username = "testUser")
    public void addMembersShouldReturnUpdatedSpaceObject(){
        
    }

    //TODO: Add Members to the Space ( Success )
}
