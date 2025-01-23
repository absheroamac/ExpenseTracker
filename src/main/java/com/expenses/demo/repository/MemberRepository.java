package com.expenses.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenses.demo.entities.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
    
}
