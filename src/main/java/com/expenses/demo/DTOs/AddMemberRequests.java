package com.expenses.demo.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddMemberRequests {

    private Long spaceId;
    private List<Long> userIds;

}
