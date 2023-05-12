package com.ssafy.enjoytrip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.service.NoticeService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@RestController
@RequestMapping("/notice/api")
public class RestNoticeController {
	
	NoticeService noticeService;

	@Autowired
	public RestNoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> list(String pgno, String key, String word){
		Map<String, Object> map = new HashMap<String, Object>();
		List<NoticeDto> noticeList = null;
		try {
			noticeList = noticeService.listNotice(pgno, key, word);
			PageNavigation navigation = noticeService.makePageNavigation(pgno, key, word);
			map.put("data", noticeList);
			map.put("page", navigation);
			map.put("msg", "조회성공");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("msg", "조회실패");
			return null;
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(int noticeNo){
		try {
			noticeService.deleteNotice(noticeNo);
			return ResponseEntity.ok("삭제완료");
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/{noticeNo}")
	public ResponseEntity<NoticeDto> view(@PathVariable int noticeNo){
		try {
			noticeService.hitNotice(noticeNo);
			NoticeDto notice = noticeService.getNoticeByNoticeNo(noticeNo);
			return ResponseEntity.ok(notice);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> write(@RequestBody String noticeTitle, @RequestBody String noticeContent, HttpSession session){
		try {
			UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
			NoticeDto noticeDto = new NoticeDto();
	    	noticeDto.setNoticeTitle(noticeTitle);
	    	noticeDto.setNoticeContent(noticeContent);
	    	noticeDto.setUserId(userDto.getUserId());
	    	noticeService.writeNotice(noticeDto);
	    	return ResponseEntity.ok("글쓰기 완료");
		} catch (Exception e) {
			return null;
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modify(@RequestBody NoticeDto noticeDto){
		try {
			System.out.println(noticeDto.getNoticeTitle());
			noticeService.updateNotice(noticeDto.getNoticeNo(), noticeDto.getNoticeTitle(), noticeDto.getNoticeContent());
//			System.out.println(noticeTitle);
			return ResponseEntity.ok("수정완료");
		} catch (Exception e) {
			return null;
		}
	}
//	@PutMapping("/modify")
//	public ResponseEntity<String> modify(@RequestBody int noticeNo, @RequestBody String noticeTitle,@RequestBody String noticeContent){
//		System.out.println(noticeNo+" "+noticeTitle+" "+noticeContent);
//		try {
//			System.out.println(noticeTitle);
//			noticeService.updateNotice(noticeNo, noticeTitle, noticeContent);
//			System.out.println(noticeTitle);
//			return ResponseEntity.ok("수정완료");
//		} catch (Exception e) {
//			return null;
//		}
//	}

}
