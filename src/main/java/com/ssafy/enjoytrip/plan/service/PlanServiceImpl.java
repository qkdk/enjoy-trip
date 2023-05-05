package com.ssafy.enjoytrip.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.repository.PlanRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, ObjectMapper objectMapper) {
        this.planRepository = planRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void writePlan(PlanWriteRequestDto planWriteRequestDto, String userId) {
        Map map = objectMapper.convertValue(planWriteRequestDto, Map.class);
        map.put("userId", userId);
        planRepository.insertPlan(map);
        planRepository.insertPlanDetail(map);
    }
}
