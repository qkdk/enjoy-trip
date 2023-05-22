package com.ssafy.enjoytrip.Attraction.service;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.Attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.enums.AttractionConstant;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    @Override
    public List<AttractionDto> getAttraction(int sidoCode, int gugunCode, int contentTypeId, String word) {
        List<AttractionDto> attractionDtos = null;
        if (gugunCode == 0 && contentTypeId == 0) {
            attractionDtos = attractionRepository
                    .getAttractionBySidoCode(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            word);
        }
        else if (gugunCode != 0 && contentTypeId == 0) {
            attractionDtos = attractionRepository
                    .getAttractionBySidoCodeAndGugunCode(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            gugunCode,
                            word);
        }
        else if (gugunCode == 0 && contentTypeId != 0) {
            attractionDtos = attractionRepository
                    .getAttractionBySidoCodeAndContentTypeId(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            contentTypeId,
                            word);
        } else {
            attractionDtos = attractionRepository
                    .getAttractionBySidoCodeAndGugunCodeAndContentTypeId(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            gugunCode,
                            contentTypeId,
                            word);
        }

        return attractionDtos;
    }

    @Override
    public List<SidoCodeDto> getSidoCodeAndName() {
        return attractionRepository.getSidoCodeAndName();
    }

    @Override
    public List<GugunCodeDto> getGugunCodeAndName(int sidoCode) {
        return attractionRepository.getGugunCodeAndNameBySidoCode(sidoCode);
    }

    @Override
    public String getAttractionDescription(String contentId) {
        return attractionRepository.getAttractionDescription(contentId);
    }

}
