package com.ssafy.enjoytrip.plan.dto;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailDto {

    private PlanDto planInfo;
    private List<AttractionDto> attractionList;
}
