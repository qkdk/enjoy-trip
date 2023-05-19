package com.ssafy.enjoytrip.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.repository.PlanRepository;
import java.util.List;
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

    @Override

    public List<PlanDto> listPlan(int pgno, String key, String word, String order) {
        return planRepository.getPlan(key, word,
                (pgno - 1) * PageConstant.LIST_SIZE.getValue(), PageConstant.LIST_SIZE.getValue(), order);
    }

    @Override
    public PlanDetailDto viewPlan(int planId) {
        try {
            List<AttractionDto> attractionsByPlanId = planRepository.getAttractionsByPlanId(planId);
            PlanDto planByPlanId = planRepository.getPlanByPlanId(planId);

            return PlanDetailDto.builder()
                    .planInfo(planByPlanId)
                    .attractionList(attractionsByPlanId)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("데이터베이스 오류가 발생했습니다.");
        }
    }
}
