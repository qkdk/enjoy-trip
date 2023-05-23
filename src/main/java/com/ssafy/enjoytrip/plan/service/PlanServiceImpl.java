package com.ssafy.enjoytrip.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.repository.PlanRepository;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            List<Map> attractionsByPlanId = planRepository.getAttractionsByPlanId(planId);
            PlanDto planByPlanId = planRepository.getPlanByPlanId(planId);
            List<AttractionDto> attractionDtos = new ArrayList<>();

            attractionsByPlanId.stream()
                    .filter(Objects::nonNull)
                    .forEach(map -> convertAttractionMapToDto(attractionDtos, map));

            return PlanDetailDto.builder()
                    .planInfo(planByPlanId)
                    .attractionList(attractionDtos)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("데이터베이스 오류가 발생했습니다.");
        }
    }

    @Override
    public int updatePlanRecommend(int planId) {
        String userId = SecurityUtil.getCurrentUserId().get();
        try {
            planRepository.insertPlanRecommend(userId, planId);
            return planRepository.updatePlanRecommend(planId);
        } catch (Exception e) {
            throw new RuntimeException("이미 추천한 사용자 입니다.");
        }
    }

    @Override
    public List<PlanDto> listUserPlan(String userId) {
        return planRepository.getPlanByUserId(userId);
//        try {
//        } catch (Exception e) {
//            throw new RuntimeException("계획 불러오기가 실패했습니다.");
//        }
    }

    private static void convertAttractionMapToDto(List<AttractionDto> attractionDtos, Map map) {
        attractionDtos.add(AttractionDto.builder()
                .contentId((Integer) map.get("content_id"))
                .readCount((Integer) map.get("readcount"))
                .addr2((String) map.get("addr2"))
                .addr1((String) map.get("addr1"))
                .firstImage((String) map.get("first_image"))
                .latitude(((BigDecimal) map.get("latitude")).doubleValue())
                .contentTypeId((Integer) map.get("content_type_id"))
                .title((String) map.get("title"))
                .zipCode((String) map.get("zipcode"))
                .sidoCode((Integer) map.get("sido_code"))
                .mLevel((String) map.get("mlevel"))
                .gugunCode((Integer) map.get("gugun_code"))
                .tel((String) map.get("tel"))
                .firstImage2((String) map.get("first_image2"))
                .longitude(((BigDecimal) map.get("longitude")).doubleValue())
                .build()
        );
    }
}
