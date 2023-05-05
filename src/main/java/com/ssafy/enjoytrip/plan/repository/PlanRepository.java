package com.ssafy.enjoytrip.plan.repository;

import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanRepository{

    int insertPlan(Map map);

    int insertPlanDetail(Map map);
}
