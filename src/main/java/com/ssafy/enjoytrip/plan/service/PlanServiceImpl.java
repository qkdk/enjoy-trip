package com.ssafy.enjoytrip.plan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.repository.PlanRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public int writePlan(PlanWriteRequestDto planWriteRequestDto, String userId) {
        checkWriteRequestDto(planWriteRequestDto);
        Map<String, Object> map = objectMapper.convertValue(planWriteRequestDto, Map.class);
        map.put("userId", userId);
        planRepository.insertPlan(map);
        return planRepository.insertPlanDetail(map);
    }

    @Override
    public List<PlanDto> listPlan(int pgno, String key, String word, String order) {
        return planRepository.getPlan(key, word,
                (pgno - 1) * PageConstant.LIST_SIZE.getValue(), PageConstant.LIST_SIZE.getValue(), order);
    }

    @Override
    @Transactional
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
    @Transactional
    public int updatePlanRecommend(int planId, String userId, int value) {
        try {
            if (value == 1) {
                planRepository.insertPlanRecommend(userId, planId);
            } else if (value == -1) {
                planRepository.deletePlanRecommend(userId, planId);
            } else {
                throw new RuntimeException("잘못된 증감값입니다.");
            }
            return planRepository.updatePlanRecommend(planId, value);
        } catch (Exception e) {
            throw new RuntimeException("이미 추천한 사용자 입니다.");
        }
    }

    @Override
    public List<PlanDto> listUserPlan(String userId) {
        try {
            return planRepository.getPlanByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("계획 불러오기가 실패했습니다.");
        }
    }

    @Override
    public int copyPlan(int planId, String userId) {
        if (planRepository.getPlanByPlanIdAndUserId(planId, userId).isPresent()) {
            throw new RuntimeException("자기의 계획은 복사 할 수 없습니다.");
        }

        try {
            PlanWriteRequestDto dto = PlanWriteRequestDto.builder()
                    .planTitle(userId + "님의 계획 복사본")
                    .contentIdList(planRepository.getContentIdByPlanId(planId))
                    .startDate(getCurentTime())
                    .endDate(getCurentTime())
                    .build();

            return writePlan(dto, userId);

        } catch (Exception e) {
            throw new RuntimeException("카피중 오류 발생");
        }

    }

    @Override
    public int deletePlan(int planId, String userId) {
        int resultRow = planRepository.deletePlanByUserIdAndPlanId(planId, userId);
        if (resultRow == 0) {
            throw new RuntimeException("일치하는 정보가 없습니다.");
        }
        return resultRow;
    }

    @Override
    public List<String> getRecommendList(String userId) {
        return planRepository.getRecommendByUserId(userId);
    }

    private static String getCurentTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(currentDate);
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

    private static void checkWriteRequestDto(PlanWriteRequestDto planWriteRequestDto) {
        if (!StringUtils.hasText(planWriteRequestDto.getPlanTitle())) {
            throw new RuntimeException("제목 입력은 필수 입니다.");
        }
        if (!StringUtils.hasText(planWriteRequestDto.getEndDate())) {
            throw new RuntimeException("일정을 정해주세요");
        }
        if (!StringUtils.hasText(planWriteRequestDto.getStartDate())) {
            throw new RuntimeException("일정을 정해주세요");
        }
        if (planWriteRequestDto.getContentIdList().isEmpty()) {
            throw new RuntimeException("관광지를 선택해주세요");
        }
    }
}
