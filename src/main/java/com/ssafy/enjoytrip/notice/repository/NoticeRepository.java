package com.ssafy.enjoytrip.notice.repository;

import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeRepository {

    List<NoticeDto> getNotice(int offset, int limit, String key, String word) throws SQLException;

    List<NoticeDto> getNoticeSort(int offset, int limit, String key, String word) throws SQLException;
    
    int getTotalNoticeCount(String key, String word) throws SQLException;

    NoticeDto getNoticeByNoticeNo(int noticeNo) throws SQLException;
    
    void writeNotice(NoticeDto noticeDto) throws Exception;
    
    void updateNotice(int noticeNo, String noticeTitle, String noticeContent) throws Exception;

    void deleteNotice(int noticeNo) throws Exception;
    
    void hitNotice(int noticeNo) throws Exception;
    
    List<NoticeDto> getList(String key, String word) throws Exception;
}
