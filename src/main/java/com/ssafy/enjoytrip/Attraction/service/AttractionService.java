package com.ssafy.enjoytrip.Attraction.service;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

    List<AttractionDto> getAttraction(int sidoCode, int gugunCode, int contentTypeId, String word);

    List<SidoCodeDto> getSidoCodeAndName() ;

    List<GugunCodeDto> getGugunCodeAndName(int sidoCode);

    String getAttractionDescription(String contentId);

}
