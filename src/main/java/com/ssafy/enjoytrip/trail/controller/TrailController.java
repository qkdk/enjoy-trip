package com.ssafy.enjoytrip.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.Attraction.dto.GugunCodeDto;
import com.ssafy.enjoytrip.Attraction.dto.SidoCodeDto;
import com.ssafy.enjoytrip.trail.dto.CityDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardDto;
import com.ssafy.enjoytrip.trail.dto.TrailBoardReplyDto;
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
	
	@GetMapping("/board/view")
	public ResponseEntity<TrailBoardDto> getLatestBoard() throws Exception{
		int trail_board_no = trailService.lastIndex();
		return new ResponseEntity<TrailBoardDto>(trailService.getLatestBoard(trail_board_no),HttpStatus.OK);
	}
	
	@PostMapping("/board/joinparty")
	public ResponseEntity<String> joinParty(@RequestBody TrailBoardDto trailBoardDto) throws Exception{
		trailService.joinParty(trailBoardDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/joinmember/{no}")
	public ResponseEntity<List<TrailBoardDto>> joinMember(@PathVariable int no) throws Exception{
		return new ResponseEntity<List<TrailBoardDto>>(trailService.joinMember(no),HttpStatus.OK);
	}
	
	@DeleteMapping("/board/delete/{no}")
	public ResponseEntity<String> delete(@PathVariable int no) throws Exception{
		trailService.deleteTrailParty(no);
		trailService.deleteTrailBoard(no);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping("/board/modify")
	public ResponseEntity<String> modify(@RequestBody TrailBoardDto trailBoardDto) throws Exception{
		trailService.trailBoardUpdate(trailBoardDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/getcity/{trailId}")
	public ResponseEntity<CityDto> getCity(@PathVariable int trailId) throws Exception{
		return new ResponseEntity<CityDto>(trailService.getCityName(trailId),HttpStatus.OK);
	}
	
	@GetMapping("/board/reply/{no}")
	public ResponseEntity<List<TrailBoardReplyDto>> getReply(@PathVariable int no) throws Exception{
		return new ResponseEntity<List<TrailBoardReplyDto>>(trailService.getReply(no),HttpStatus.OK);
	}
	
	@PostMapping("/board/reply/write")
	public ResponseEntity<String> setReply(@RequestBody TrailBoardReplyDto trailBoardReplyDto) throws Exception{
		trailService.setReply(trailBoardReplyDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
