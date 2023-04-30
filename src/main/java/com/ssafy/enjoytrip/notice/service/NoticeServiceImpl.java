package com.ssafy.enjoytrip.notice.service;

import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.repository.NoticeRepository;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    SqlSession session;

    @Autowired
    public NoticeServiceImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<NoticeDto> listNotice(String pgno, String key, String word) throws SQLException {
        // key 는 notice_title, notice_content, notice_id 로 올수있다.
        // 여기서변환해주는 작업 필요할수도
        // 혹은 프론트에서 알맞은 값으로 넘기거나
        //...

        int totalNoticeCount = session.getMapper(NoticeRepository.class).getTotalNoticeCount(key, word);
        List<NoticeDto> notice = session.getMapper(NoticeRepository.class)
                .getNotice(Integer.parseInt(pgno) - 1, PageConstant.LIST_SIZE.getValue(), key, word);

        System.out.println(totalNoticeCount);
        System.out.println(notice);

        return null;
    }
}
