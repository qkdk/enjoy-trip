package com.ssafy.enjoytrip.notice.service;

import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import java.sql.SQLException;
import java.util.List;

public interface NoticeService {

    List<NoticeDto> listNotice(String pgno, String key, String word) throws SQLException;

}
