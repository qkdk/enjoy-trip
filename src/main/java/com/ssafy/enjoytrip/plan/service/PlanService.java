package com.ssafy.enjoytrip.plan.service;

import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import java.util.List;

public interface PlanService {

    void writePlan(PlanWriteRequestDto planWriteRequestDto, String userId);

    List<PlanDto> listPlan(int pgno, String key, String word);

    PlanDetailDto viewPlan(int planNo);
}
