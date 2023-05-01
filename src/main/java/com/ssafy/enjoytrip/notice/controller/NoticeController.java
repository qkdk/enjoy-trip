package com.ssafy.enjoytrip.notice.controller;

import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.service.NoticeService;
import com.ssafy.enjoytrip.util.PageNavigation;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
