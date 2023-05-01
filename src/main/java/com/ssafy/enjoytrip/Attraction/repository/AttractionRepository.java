package com.ssafy.enjoytrip.Attraction.repository;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionRepository {

    List<AttractionDto> getAttractionBySidoCode(int offset, int limit, int sidoCode);

    List<AttractionDto> getAttractionBySidoCodeAndGugunCode(int offset, int limit, int sidoCode, int gugunCode);

    List<AttractionDto> getAttractionBySidoCodeAndContentTypeId(int offset, int limit, int sidoCode,
            int contentTypeId);

    List<AttractionDto> getAttractionBySidoCodeAndGugunCodeAndContentTypeId(int offset, int limit, int sidoCode,
            int gugunCode, int contentTypeId);


}
