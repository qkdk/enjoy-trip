package com.ssafy.enjoytrip.trail.service;

import java.util.List;


import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardDto;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;

public interface TrailService {

	List<SidoCodeDto> getSido() throws Exception;

	List<GugunCodeDto> getGugun(int sido) throws Exception;

	List<TrailInputDto> getTrail(int sido, int gugun) throws Exception;

	TrailInputDto view(int trail_id) throws Exception;
	
	List<TrailBoardDto> trailBoardList(String key, String word) throws Exception;
	
	void writeTrailBoard(TrailBoardDto trailBoardDto) throws Exception;
	
	int lastIndex() throws Exception;
	
	void writeTrailParty(int trail_party_id, String trail_party_member_id, int trail_board_max_member, int trail_board_member_count) throws Exception;

}
