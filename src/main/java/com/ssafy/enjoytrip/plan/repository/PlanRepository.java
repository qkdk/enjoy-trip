package com.ssafy.enjoytrip.plan.repository;

import com.ssafy.enjoytrip.plan.dto.PlanDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanRepository {

    int insertPlan(Map map);

    int insertPlanDetail(Map map);

    List<PlanDto> getPlan(String key, String word, int offset, int limit, String order);

    PlanDto getPlanByPlanId(int planId);

    List<Map> getAttractionsByPlanId(int planId);

//    String getUserIdByPlanIdAndUserIdFromRecommend(String userId, int planId);

    int insertPlanRecommend(String userId, int planId);

    int deletePlanRecommend(String userId, int planId);


    int updatePlanRecommend(int planId, int value);

    List<PlanDto> getPlanByUserId(String userId);

    List<String> getContentIdByPlanId(int planId);

    int deletePlanByUserIdAndPlanId(int planId, String userId);

    Optional<PlanDto> getPlanByPlanIdAndUserId(int planId, String userId);

    List<String> getRecommendByUserId(String userId);

}
