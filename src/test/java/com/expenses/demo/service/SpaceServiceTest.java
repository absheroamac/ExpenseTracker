package com.expenses.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expenses.demo.DTOs.AddMemberRequests;
import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Member;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.repository.MemberRepository;
import com.expenses.demo.repository.SpaceRepository;
import com.expenses.demo.repository.UserRepository;
import com.expenses.demo.services.SpaceService;

@RunWith(MockitoJUnitRunner.class)
public class SpaceServiceTest {

    @Mock
    SpaceRepository repository;

    @Mock
    MemberRepository memberRepository;

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
    @Test
    public void addMemberstToSpaceShouldReturnUpdatedSpaceObject() {

        List<Long> users = new ArrayList<>();
        users.add(0l);
        users.add(1l);

        AddMemberRequests requests = new AddMemberRequests(3L, users);

        User user = new User(0l, "name", "email", "password", 3000.00);
        Space space = new Space(0L, "name", user, 3000.00, 3000.00, 3000.00, 3000.00);
        Member member = new Member(0L, user.getIncome(), null, user, space);
        when(userRepository.getReferenceById(anyLong())).thenReturn(user);
        when(repository.getReferenceById(anyLong())).thenReturn(space);

        Space expected = new Space(0L, "name", user, 9000.00, 3000.00, 3000.00, 3000.00);

        when(repository.save(expected)).thenReturn(expected);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Space actual = spaceService.addMembers(requests);

        assertEquals(expected, actual);

        // take space object from the repo
        // on each iteration take user info from repo
        // create member object and save to the member repo add contribution to the
        // total budgent of the space

        // return the updated space

    }

}
