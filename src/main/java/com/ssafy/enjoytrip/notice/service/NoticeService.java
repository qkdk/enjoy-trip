package com.ssafy.enjoytrip.notice.service;

import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import java.sql.SQLException;
import java.util.List;

public interface NoticeService {

    List<NoticeDto> listNotice(String pgno, String key, String word) throws SQLException;

    PageNavigation makePageNavigation(String pgno, String key, String word) throws SQLException;

    NoticeDto getNoticeByNoticeNo(int noticeNo) throws SQLException;

    void writeNotice(NoticeDto noticeDto) throws Exception;
}
