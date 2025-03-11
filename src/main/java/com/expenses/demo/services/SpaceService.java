package com.expenses.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses.demo.DTOs.AddMemberRequests;
import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Member;
import com.expenses.demo.entities.Space;
import com.expenses.demo.entities.User;
import com.expenses.demo.enums.Role;
import com.expenses.demo.repository.MemberRepository;
import com.expenses.demo.repository.SpaceRepository;
import com.expenses.demo.repository.UserRepository;

@Service
public class SpaceService {

    SpaceRepository spaceRepository;
    UserRepository userRepository;
    MemberRepository memberRepository;

    public SpaceService(SpaceRepository spaceRepository, UserRepository userRepository,
            MemberRepository memberRepository) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    public Space createSpace(SpaceCreationRequest request) {
        Space space = new Space(null, request.getName(), null, request.getTotalBudget(), request.getTotalBudget(),
                request.getLivingExpense(), request.getTotalExpense());
        User user = userRepository.getReferenceById(request.getUserID());
        space.setAdmin(user);

        return spaceRepository.save(space);

    }

    public Space addMembers(AddMemberRequests requests) {
        // TODO Auto-generated method stub
        Space space = spaceRepository.getReferenceById(requests.getSpaceId());

        for (Long id : requests.getUserIds()) {
            User user = userRepository.getReferenceById(id);
            Member member = new Member(user.getId(), user.getIncome(), null, user, space);
            memberRepository.save(member);

            space.setTotalBudget(space.getTotalBudget() + member.getContribution());
        }

        return spaceRepository.save(space);

    }

}
