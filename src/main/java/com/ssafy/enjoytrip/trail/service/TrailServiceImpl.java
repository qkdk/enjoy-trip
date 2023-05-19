package com.ssafy.enjoytrip.trail.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
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

}
