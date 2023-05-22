package com.ssafy.enjoytrip.Attraction.repository;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionRepository {

    List<AttractionDto> getAttractionBySidoCode(int offset, int limit, int sidoCode, String word);

    List<AttractionDto> getAttractionBySidoCodeAndGugunCode(int offset, int limit, int sidoCode, int gugunCode,
            String word);

    List<AttractionDto> getAttractionBySidoCodeAndContentTypeId(int offset, int limit, int sidoCode,
            int contentTypeId, String word);

    List<AttractionDto> getAttractionBySidoCodeAndGugunCodeAndContentTypeId(int offset, int limit, int sidoCode,
            int gugunCode, int contentTypeId, String word);

    List<SidoCodeDto> getSidoCodeAndName();

    List<GugunCodeDto> getGugunCodeAndNameBySidoCode(int sidoCode);

    int getSidoCodeBySidoName(String sidoName);

    int getGugunCodeBySidoCodeAndGugunName(String gugunName, int sidoCode);

    String getAttractionDescription(String contentId);

}
