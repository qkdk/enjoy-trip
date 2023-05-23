package com.ssafy.enjoytrip.plan.service;

import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import java.util.List;

public interface PlanService {

    int writePlan(PlanWriteRequestDto planWriteRequestDto, String userId);

    List<PlanDto> listPlan(int pgno, String key, String word, String order);

    PlanDetailDto viewPlan(int planId);

    int updatePlanRecommend(int planId, String userId, int value);

    List<PlanDto> listUserPlan(String userId);

    int copyPlan(int planId, String userId);

    int deletePlan(int planId, String userId);

    List<String> getRecommendList(String userId);
}
