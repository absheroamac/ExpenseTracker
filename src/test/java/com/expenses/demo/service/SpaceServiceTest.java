package com.expenses.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.repository.SpaceRepository;
import com.expenses.demo.repository.UserRepository;
import com.expenses.demo.services.SpaceService;

@ExtendWith(MockitoExtension.class)
public class SpaceServiceTest {

    @Mock
    SpaceRepository repository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    SpaceService spaceService;

    // TODO : Create Space should return created Space object with ID.

    @Test
    public void createSpaceShouldReturnNewlyCreatedSpaceObject() {

        SpaceCreationRequest request = new SpaceCreationRequest("name", 0L, 3000, 3000, 3000, 3000);
        User user = new User(0L, "Name", "email", "password", 0);
        Space expected = new Space(0L, "name", user, 3000.00, 3000.00, 3000.00, 3000.00);
        when(userRepository.getReferenceById(anyLong())).thenReturn(user);
        when(repository.save(any(Space.class))).thenReturn(expected);
        Space actual = spaceService.createSpace(request);
        assertEquals(expected, actual);

    }

    // TODO : AddMembers Should Return Modified Space Object

}
