package com.ssafy.enjoytrip.plan.service;

import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;

public interface PlanService {

    void writePlan(PlanWriteRequestDto planWriteRequestDto, String userId);
}
