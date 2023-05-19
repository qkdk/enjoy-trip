package com.ssafy.enjoytrip.notice.service;

import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import java.sql.SQLException;
import java.util.List;

public interface NoticeService {

    List<NoticeDto> listNotice(String pgno, String key, String word) throws SQLException;
   
    List<NoticeDto> listNoticeSort(String pgno, String key, String word) throws SQLException;

    PageNavigation makePageNavigation(String pgno, String key, String word) throws SQLException;

    NoticeDto getNoticeByNoticeNo(int noticeNo) throws SQLException;

    void writeNotice(NoticeDto noticeDto) throws Exception;
    
    void updateNotice(int noticeNo, String noticeTitle, String noticeContent) throws Exception;

    void deleteNotice(int noticeNo) throws Exception;
    
    void hitNotice(int noticeNo) throws Exception;
    
    List<NoticeDto> getList(String key, String word) throws Exception;
}
