package com.ssafy.enjoytrip.trail.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.CityDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardReplyDto;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;
import com.ssafy.enjoytrip.trail.repository.TrailRepository;

@Service
public class TrailServiceImpl implements TrailService {
	
	SqlSession sqlSession;
	
	@Autowired
	public TrailServiceImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}



	@Override
	public List<SidoCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getSido();
	}



	@Override
	public List<GugunCodeDto> getGugun(int sido) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getGugun(sido);
	}



	@Override
	public List<TrailInputDto> getTrail(int sido, int gugun) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getTrail(sido, gugun);
	}



	@Override
	public TrailInputDto view(int trail_id) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).view(trail_id);
	}



	@Override
	public List<TrailBoardDto> trailBoardList(String key, String word) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).trailBoardList(key, word);
	}



	@Override
	public void writeTrailBoard(TrailBoardDto trailBoardDto) throws Exception {
		sqlSession.getMapper(TrailRepository.class).writeTrailBoard(trailBoardDto);
	}



	@Override
	public int lastIndex() throws Exception {
		return sqlSession.getMapper(TrailRepository.class).lastIndex();
	}



	@Override
	public void writeTrailParty(int trail_party_id, String trail_party_member_id, int trail_board_max_member, int trail_board_member_count)
			throws Exception {
		sqlSession.getMapper(TrailRepository.class).writeTrailParty(trail_party_id, trail_party_member_id, trail_board_max_member, trail_board_member_count);
	}



	@Override
	public TrailBoardDto getLatestBoard(int trail_board_no) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getLatestBoard(trail_board_no);
	}



	@Override
	public void joinParty(TrailBoardDto trailBoardDto) throws Exception {
		sqlSession.getMapper(TrailRepository.class).joinParty(trailBoardDto);
	}



	@Override
	public List<TrailBoardDto> joinMember(int no) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).joinMember(no);
	}



	@Override
	public void deleteTrailParty(int no) throws Exception {
		sqlSession.getMapper(TrailRepository.class).deleteTrailParty(no);
	}



	@Override
	public void deleteTrailBoard(int no) throws Exception {
		sqlSession.getMapper(TrailRepository.class).deleteTrailBoard(no);
	}



	@Override
	public void trailBoardUpdate(TrailBoardDto trailBoardDto) throws Exception {
		sqlSession.getMapper(TrailRepository.class).trailBoardUpdate(trailBoardDto);
	}



	@Override
	public CityDto getCityName(int trailId) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getCityName(trailId);
	}



	@Override
	public List<TrailBoardReplyDto> getReply(int no) throws Exception {
		return sqlSession.getMapper(TrailRepository.class).getReply(no);
	}



	@Override
	public void setReply(TrailBoardReplyDto trailBoardReplyDto) throws Exception {
		sqlSession.getMapper(TrailRepository.class).setReply(trailBoardReplyDto);
	}

}
