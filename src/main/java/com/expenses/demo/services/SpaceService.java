package com.expenses.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.repository.SpaceRepository;
import com.expenses.demo.repository.UserRepository;

@Service
public class SpaceService {

    SpaceRepository spaceRepository;
    UserRepository userRepository;

    public SpaceService(SpaceRepository spaceRepository, UserRepository userRepository) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
    }

    public Space createSpace(SpaceCreationRequest request) {
        Space space = new Space(null, request.getName(), null, request.getTotalBudget(), request.getTotalBudget(),
                request.getLivingExpense(), request.getTotalExpense());
        User user = userRepository.getReferenceById(request.getUserID());
        space.setAdmin(user);

        return spaceRepository.save(space);

    }

    public Object addMembers(List any) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMembers'");
    }

}
