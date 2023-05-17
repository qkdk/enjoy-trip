package com.ssafy.enjoytrip.trail.repository;

import com.ssafy.enjoytrip.trail.dto.TrailInputDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrailRepository {

    void insertData(List<TrailInputDto> trailInputDtos);
}
