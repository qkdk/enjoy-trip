package com.ssafy.enjoytrip.place.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.place.dto.RecommendDto;
import com.ssafy.enjoytrip.place.dto.ReplyDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface PlaceService {
	List<PlaceDto> list(String pgno, String key, String word) throws Exception;

	List<PlaceDto> getList() throws Exception;
	
	PageNavigation makePageNavigation(String pgno, String key, String word) throws Exception;

	PlaceDto getPlaceByPlaceNo(int placeNo) throws SQLException;
	
	void writePlace(PlaceDto placeDto) throws Exception;
	
	void writePlaceImg(int placeNo, String placeImgSrc) throws Exception;
	
	PlaceDto view(int placeNo)throws SQLException;
	
	int lastIndex() throws SQLException;
	
	void deletePlace(int placeNo) throws SQLException;

	void deletePlaceImg(int placeNo) throws SQLException;
	
	void modifyPlace(int placeNo,String placeTitle, String placeContent) throws SQLException;
	
	int checkImg(int placeNo) throws Exception;
	
	List<ReplyDto> replyList(int placeNo) throws Exception;
	
	void writeReply(ReplyDto replyDto) throws Exception;
	
	List<RecommendDto> recommend(int no) throws Exception;
	
	void addRecommend(RecommendDto recommendDto) throws Exception;
	
	void delRecommend(RecommendDto recommendDto) throws Exception;
	
	void modifyRecommend(int count, int placeNo) throws Exception;

	List<PlaceDto> listSort(String pgno, String key, String word) throws Exception;
}
