package com.ssafy.enjoytrip.place.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.place.dto.PlaceDto;
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
                .getPlace((Integer.parseInt(pgno) - 1) * PageConstant.LIST_SIZE.getValue(),
                        PageConstant.LIST_SIZE.getValue(), key, word);
	}


	@Override
    public PageNavigation makePageNavigation(String pgno, String key, String word) throws SQLException {
        PageNavigation pageNavigation = new PageNavigation();

        pageNavigation.setWord(word);
        pageNavigation.setKey(key);

        int naviSize = PageConstant.NAVIGATION_SIZE.getValue();
        int sizePerPage = PageConstant.LIST_SIZE.getValue();
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

}
