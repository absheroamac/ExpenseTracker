package com.expenses.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Space;
import com.expenses.demo.services.SpaceService;

@RestController
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    SpaceService service;

    @PostMapping("/newspace")
    public ResponseEntity<Space> createNewSpace(@RequestBody SpaceCreationRequest request) {

        try {
            Space space = service.createSpace(request);
            return ResponseEntity.ok(space);
        } catch (Exception err) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
