package com.ssafy.enjoytrip.plan.service;

import com.ssafy.enjoytrip.plan.dto.PlanListResponseDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import java.util.List;

public interface PlanService {

    void writePlan(PlanWriteRequestDto planWriteRequestDto, String userId);

    List<PlanListResponseDto> listPlan(int pgno, String key, String word);
}
