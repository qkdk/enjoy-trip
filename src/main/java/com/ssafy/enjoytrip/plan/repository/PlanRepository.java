package com.ssafy.enjoytrip.plan.repository;

import com.ssafy.enjoytrip.plan.dto.PlanListResponseDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanRepository{

    int insertPlan(Map map);

    int insertPlanDetail(Map map);

    List<PlanListResponseDto> getPlan(String key, String word, int offset, int limit);
}
