package com.ssafy.enjoytrip.plan.repository;

import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanRepository{

    int insertPlan(Map map);

    int insertPlanDetail(Map map);

    List<PlanDto> getPlan(String key, String word, int offset, int limit, String order);

    PlanDetailDto getPlanAndAttractionsByPlanNo(int planNo);

}
