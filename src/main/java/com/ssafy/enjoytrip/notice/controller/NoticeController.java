package com.ssafy.enjoytrip.notice.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.service.NoticeService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("")
    public String listNotice(String pgno, String key, String word, Model model) throws SQLException {
        List<NoticeDto> noticeList = noticeService.listNotice(pgno, key, word);
        PageNavigation navigation = noticeService.makePageNavigation(pgno, key, word);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("navigation", navigation);
        return "/notice/list";
    }
    
    @GetMapping("/sort")
    public String listSort(String pgno, String key, String word, Model model) throws SQLException {
    	List<NoticeDto> noticeList = noticeService.listNoticeSort(pgno, key, word);
    	PageNavigation navigation = noticeService.makePageNavigation(pgno, key, word);
    	
    	model.addAttribute("noticeList", noticeList);
    	model.addAttribute("navigation", navigation);
    	return "notice/list";
    }

    @GetMapping("/{noticeNo}")
    public String viewNotice(@PathVariable int noticeNo, Model model) throws Exception {
        // noticeNo로 notice정보 불러오기
        // model에 담아서 전송
    	noticeService.hitNotice(noticeNo);
        NoticeDto notice = noticeService.getNoticeByNoticeNo(noticeNo);
//        notice.setNoticeHit(notice.getNoticeHit()+1);
        model.addAttribute("notice", notice);

        return "notice/view";
    }
    // 수정할때는 세션의 값과 비교해서
    
    
    @GetMapping("/write")
    public String write(HttpSession session) {
    	return "notice/write";
    }
    
    @PostMapping("/write")
    public String write(String noticeTitle, String noticeContent, HttpSession session) throws Exception {
    	UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
    	NoticeDto noticeDto = new NoticeDto();
    	noticeDto.setNoticeTitle(noticeTitle);
    	noticeDto.setNoticeContent(noticeContent);
    	noticeDto.setUserId(userDto.getUserId());
    	noticeService.writeNotice(noticeDto);
    	return "redirect:/notice?pgno=1&key&word=";
    }
    
    @GetMapping("/modify")
    public ModelAndView modify(int noticeNo, ModelAndView mv) throws SQLException {
    	NoticeDto notice = noticeService.getNoticeByNoticeNo(noticeNo);
    	mv.addObject("notice", notice);
    	mv.setViewName("/notice/modify");
    	return mv;
    }
    
    
    @PostMapping("/modify")
    public String modify(int noticeNo, String noticeTitle, String noticeContent) throws Exception {
    	noticeService.updateNotice(noticeNo, noticeTitle, noticeContent);
    	return "redirect:/notice?pgno=1&key&word=";
    }
    @GetMapping("/delete")
    public String delete(int noticeNo) throws Exception {
    	noticeService.deleteNotice(noticeNo);
    	return "redirect:/notice?pgno=1&key&word=";
    }
}
