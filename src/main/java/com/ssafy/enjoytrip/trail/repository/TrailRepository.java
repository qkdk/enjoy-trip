package com.ssafy.enjoytrip.trail.repository;

import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.CityDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardReplyDto;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrailRepository {

    void insertData(List<TrailInputDto> trailInputDtos);

	List<SidoCodeDto> getSido() throws Exception;

	List<GugunCodeDto> getGugun(int sido) throws Exception;

	List<TrailInputDto> getTrail(int sido, int gugun) throws Exception;

	TrailInputDto view(int trail_id) throws Exception;
	
	List<TrailBoardDto> trailBoardList(String key, String word) throws Exception;
	
	void writeTrailBoard(TrailBoardDto trailBoardDto) throws Exception;
	
	int lastIndex() throws Exception;
	
	void writeTrailParty(int trail_party_id, String trail_party_member_id, int trail_board_max_member, int trail_board_member_count) throws Exception;

	TrailBoardDto getLatestBoard(int trail_board_no) throws Exception;

	void joinParty(TrailBoardDto trailBoardDto) throws Exception;

	List<TrailBoardDto> joinMember(int no) throws Exception;

	void deleteTrailParty(int no) throws Exception;
	
	void deleteTrailBoard(int no) throws Exception;

	void trailBoardUpdate(TrailBoardDto trailBoardDto) throws Exception;
	
	TrailBoardDto getBoard(int no) throws Exception;
	
	CityDto getCityName(int trailId) throws Exception;
	
	List<TrailBoardReplyDto> getReply(int no) throws Exception;
	
	void setReply(TrailBoardReplyDto trailBoardReplyDto) throws Exception;
}
