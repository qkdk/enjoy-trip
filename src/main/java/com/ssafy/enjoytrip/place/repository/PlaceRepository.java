package com.ssafy.enjoytrip.place.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.place.dto.PlaceDto;

@Mapper
public interface PlaceRepository {

	List<PlaceDto> getPlace(int offset, int limit, String key, String word) throws Exception;

	int getTotalPlaceCount(String key, String word) throws SQLException;
	
	PlaceDto getPlaceByPlaceNo(int placeNo) throws SQLException;
	
	void writePlace(PlaceDto placeDto) throws Exception;
	
	void writePlaceImg(int placeNo, String placeImgSrc) throws Exception;
	
	PlaceDto view(int placeNo)throws SQLException;
	
	int lastIndex() throws SQLException;
	
	void deletePlace(int placeNo) throws SQLException;

	void deletePlaceImg(int placeNo) throws SQLException;
	
}
