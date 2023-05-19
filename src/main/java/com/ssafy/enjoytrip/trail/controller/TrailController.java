package com.ssafy.enjoytrip.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardDto;
import com.ssafy.enjoytrip.trail.dto.TrailInputDto;
import com.ssafy.enjoytrip.trail.service.TrailService;

@RestController
@RequestMapping("/trail")
public class TrailController {
	
	@Autowired
	private TrailService trailService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoCodeDto>> sido() throws Exception{
		return new ResponseEntity<List<SidoCodeDto>>(trailService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<GugunCodeDto>> gugun(@PathVariable int sido) throws Exception{
		return new ResponseEntity<List<GugunCodeDto>>(trailService.getGugun(sido), HttpStatus.OK);
	}
	
	@GetMapping("/{sido}/{gugun}")
	public ResponseEntity<List<TrailInputDto>> trail(@PathVariable int sido, @PathVariable int gugun) throws Exception{
		return new ResponseEntity<List<TrailInputDto>>(trailService.getTrail(sido, gugun), HttpStatus.OK);
	}
	
	@GetMapping("/view/{trail_id}")
	public ResponseEntity<TrailInputDto> view(@PathVariable int trail_id) throws Exception{
		return new ResponseEntity<TrailInputDto>(trailService.view(trail_id), HttpStatus.OK);
	}
	
	@GetMapping("/board")
	public ResponseEntity<List<TrailBoardDto>> boardList(String key, String word) throws Exception{
		return new ResponseEntity<List<TrailBoardDto>>(trailService.trailBoardList(key, word),HttpStatus.OK);
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestBody TrailBoardDto trailBoardDto) throws Exception{
		trailService.writeTrailBoard(trailBoardDto);
		int boardNo = trailService.lastIndex();
		trailService.writeTrailParty(boardNo, trailBoardDto.getUser_id(), trailBoardDto.getTrail_board_max_member(), trailBoardDto.getTrail_board_member_count());
		System.out.println("인풋완료");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
