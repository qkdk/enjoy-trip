package com.ssafy.enjoytrip.plan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDto {

    private String userId;
    private int planId;
    private String planTitle;
    private int recommendCount;
    private int hit;
    private String createTime;
}
