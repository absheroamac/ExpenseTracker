package com.expenses.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.demo.DTOs.SpaceCreationRequest;
import com.expenses.demo.entities.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {

    Object createSpace(SpaceCreationRequest any);

}
