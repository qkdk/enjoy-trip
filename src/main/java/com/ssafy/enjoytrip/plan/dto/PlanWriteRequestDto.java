package com.ssafy.enjoytrip.plan.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanWriteRequestDto {

    private String planTitle;
    private List<String> contentIdList;
    private String startDate;
    private String endDate;
}
