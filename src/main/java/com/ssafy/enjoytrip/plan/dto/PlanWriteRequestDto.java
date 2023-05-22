package com.ssafy.enjoytrip.plan.dto;

import java.util.List;
import lombok.Data;

@Data
public class PlanWriteRequestDto {

    private String planTitle;
    private List<String> contentIdList;
    private String startDate;
    private String endDate;
}
