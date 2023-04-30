package com.ssafy.enjoytrip.notice.controller;

import com.ssafy.enjoytrip.notice.service.NoticeService;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String listNotice(String pgno, String key, String word) throws SQLException {
        noticeService.listNotice(pgno, key, word);
        return null;
    }
}
