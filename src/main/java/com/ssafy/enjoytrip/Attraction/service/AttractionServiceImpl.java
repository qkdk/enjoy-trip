package com.ssafy.enjoytrip.Attraction.service;

import com.ssafy.enjoytrip.Attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.Attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.enums.AttractionConstant;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {

    SqlSession session;

    @Autowired
    public AttractionServiceImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<AttractionDto> getAttraction(int sidoCode, int gugunCode, int contentTypeId) throws SQLException {
        List<AttractionDto> attractionDtos = null;
        if (gugunCode == 0 && contentTypeId == 0) {
            attractionDtos = session.getMapper(AttractionRepository.class)
                    .getAttractionBySidoCode(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode);
        }
        else if (gugunCode != 0 && contentTypeId == 0) {
            attractionDtos = session.getMapper(AttractionRepository.class)
                    .getAttractionBySidoCodeAndGugunCode(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            gugunCode);
        }
        else if (gugunCode == 0 && contentTypeId != 0) {
            attractionDtos = session.getMapper(AttractionRepository.class)
                    .getAttractionBySidoCodeAndContentTypeId(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            contentTypeId);
        } else {
            attractionDtos = session.getMapper(AttractionRepository.class)
                    .getAttractionBySidoCodeAndGugunCodeAndContentTypeId(
                            AttractionConstant.ATTRACTION_OFFSET.getValue(),
                            AttractionConstant.ATTRACTION_LIMIT.getValue(),
                            sidoCode,
                            gugunCode,
                            contentTypeId);
        }

        return attractionDtos;
    }

    @Override
    public List<SidoCodeDto> getSidoCodeAndName() throws SQLException {
        return session.getMapper(AttractionRepository.class).getSidoCodeAndName();
    }

    @Override
    public List<GugunCodeDto> getGugunCodeAndName(int sidoCode) {
        return session.getMapper(AttractionRepository.class).getGugunCodeAndNameBySidoCode(sidoCode);
    }

}
