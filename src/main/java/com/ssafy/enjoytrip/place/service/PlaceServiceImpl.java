package com.ssafy.enjoytrip.place.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.place.dto.PlaceDto;
import com.ssafy.enjoytrip.place.dto.RecommendDto;
import com.ssafy.enjoytrip.place.dto.ReplyDto;
import com.ssafy.enjoytrip.place.repository.PlaceRepository;
import com.ssafy.enjoytrip.util.PageNavigation;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	SqlSession session;

	@Autowired
	public PlaceServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}


	@Override
	public List<PlaceDto> list(String pgno, String key, String word) throws Exception {
		return session.getMapper(PlaceRepository.class)
                .getPlace((Integer.parseInt(pgno) - 1) * (PageConstant.LIST_SIZE.getValue()-11),
                        PageConstant.LIST_SIZE.getValue()-11, key, word);
	}
	
	@Override
	public List<PlaceDto> listSort(String pgno, String key, String word) throws Exception {
		return session.getMapper(PlaceRepository.class)
				.getPlaceSort((Integer.parseInt(pgno) - 1) * (PageConstant.LIST_SIZE.getValue()-11),
						PageConstant.LIST_SIZE.getValue()-11, key, word);
	}


	@Override
    public PageNavigation makePageNavigation(String pgno, String key, String word) throws SQLException {
        PageNavigation pageNavigation = new PageNavigation();

        pageNavigation.setWord(word);
        pageNavigation.setKey(key);

        int naviSize = PageConstant.NAVIGATION_SIZE.getValue();
        int sizePerPage = PageConstant.LIST_SIZE.getValue()-11;
        int currentPage = Integer.parseInt(pgno);

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);

        int totalCount = session.getMapper(PlaceRepository.class).getTotalPlaceCount(key, word);
        pageNavigation.setTotalCount(totalCount);

        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);

        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);

        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);

        pageNavigation.makeNavi();

        return pageNavigation;
    }


	@Override
	public PlaceDto getPlaceByPlaceNo(int placeNo) throws SQLException {
		return session.getMapper(PlaceRepository.class).getPlaceByPlaceNo(placeNo);
	}


	@Override
	public void writePlace(PlaceDto placeDto) throws Exception {
		session.getMapper(PlaceRepository.class).writePlace(placeDto);
	}


	@Override
	public PlaceDto view(int placeNo) throws SQLException {
		return session.getMapper(PlaceRepository.class).view(placeNo);
	}


	@Override
	public void writePlaceImg(int placeNo, String placeImgSrc) throws Exception {
		session.getMapper(PlaceRepository.class).writePlaceImg(placeNo, placeImgSrc);
	}


	@Override
	public int lastIndex() throws SQLException {
		return session.getMapper(PlaceRepository.class).lastIndex();
	}


	@Override
	public void deletePlace(int placeNo) throws SQLException {
		session.getMapper(PlaceRepository.class).deletePlace(placeNo);
	}


	@Override
	public void deletePlaceImg(int placeNo) throws SQLException {
		session.getMapper(PlaceRepository.class).deletePlaceImg(placeNo);
	}


	@Override
	public void modifyPlace(int placeNo, String placeTitle, String placeContent) throws SQLException {
		session.getMapper(PlaceRepository.class).modifyPlace(placeNo, placeTitle, placeContent);
	}


	@Override
	public int checkImg(int placeNo) throws Exception {
		return session.getMapper(PlaceRepository.class).checkImg(placeNo);
	}


	@Override
	public List<ReplyDto> replyList(int placeNo) throws Exception {
		return session.getMapper(PlaceRepository.class).replyList(placeNo);
	}


	@Override
	public void writeReply(ReplyDto replyDto) throws Exception {
		session.getMapper(PlaceRepository.class).writeReply(replyDto);
	}


	@Override
	public List<PlaceDto> getList() throws Exception {
		return session.getMapper(PlaceRepository.class).getList();
	}


	@Override
	public List<RecommendDto> recommend(int no) throws Exception {
		return session.getMapper(PlaceRepository.class).recommend(no);
	}


	@Override
	public void addRecommend(RecommendDto recommendDto) throws Exception {
		session.getMapper(PlaceRepository.class).addRecommend(recommendDto);
	}


	@Override
	public void delRecommend(RecommendDto recommendDto) throws Exception {
		session.getMapper(PlaceRepository.class).delRecommend(recommendDto);
	}


	@Override
	public void modifyRecommend(int count, int placeNo) throws Exception {
		session.getMapper(PlaceRepository.class).modifyRecommend(count, placeNo);
	}

}
