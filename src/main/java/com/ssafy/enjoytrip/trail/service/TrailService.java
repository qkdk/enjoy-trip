package com.ssafy.enjoytrip.trail.service;

import java.util.List;


import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;

public interface TrailService {

	List<SidoCodeDto> getSido() throws Exception;

	List<GugunCodeDto> getGugun(int sido) throws Exception;

	List<TrailInputDto> getTrail(int sido, int gugun) throws Exception;

	TrailInputDto view(int trail_id) throws Exception;

}
