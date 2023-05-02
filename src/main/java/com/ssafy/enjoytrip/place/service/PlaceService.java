package com.ssafy.enjoytrip.place.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface PlaceService {
	List<PlaceDto> list(String pgno, String key, String word) throws Exception;

	PageNavigation makePageNavigation(String pgno, String key, String word) throws Exception;

	PlaceDto getPlaceByPlaceNo(int placeNo) throws SQLException;
	
	void writePlace(PlaceDto placeDto) throws Exception;
	
	void writePlaceImg(int placeNo, String placeImgSrc) throws Exception;
	
	PlaceDto view(int placeNo)throws SQLException;
	
	int lastIndex() throws SQLException;
}