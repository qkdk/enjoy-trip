package com.ssafy.enjoytrip.Attraction.service;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

    List<AttractionDto> getAttraction(int sidoCode, int gugunCode, int contentTypeId) throws SQLException;

}
