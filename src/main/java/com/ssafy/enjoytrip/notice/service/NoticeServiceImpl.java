package com.ssafy.enjoytrip.notice.service;

import com.ssafy.enjoytrip.enums.PageConstant;
import com.ssafy.enjoytrip.notice.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.repository.NoticeRepository;
import com.ssafy.enjoytrip.util.PageNavigation;
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
        return session.getMapper(NoticeRepository.class)
                .getNotice((Integer.parseInt(pgno) - 1) * PageConstant.LIST_SIZE.getValue(),
                        PageConstant.LIST_SIZE.getValue(), key, word);
    }
    
    @Override
	public List<NoticeDto> listNoticeSort(String pgno, String key, String word) throws SQLException {
		return session.getMapper(NoticeRepository.class)
                .getNoticeSort((Integer.parseInt(pgno) - 1) * PageConstant.LIST_SIZE.getValue(),
                        PageConstant.LIST_SIZE.getValue(), key, word);
	}

    @Override
    public PageNavigation makePageNavigation(String pgno, String key, String word) throws SQLException {
        PageNavigation pageNavigation = new PageNavigation();

        pageNavigation.setWord(word);
        pageNavigation.setKey(key);

        int naviSize = PageConstant.NAVIGATION_SIZE.getValue();
        int sizePerPage = PageConstant.LIST_SIZE.getValue();
        int currentPage = Integer.parseInt(pgno);

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);

        int totalCount = session.getMapper(NoticeRepository.class).getTotalNoticeCount(key, word);
        pageNavigation.setTotalCount(totalCount);

        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);

        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);

        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);

        pageNavigation.makeNavi();

        return pageNavigation;
    }

    @Override
    public NoticeDto getNoticeByNoticeNo(int noticeNo) throws SQLException {
        return session.getMapper(NoticeRepository.class).getNoticeByNoticeNo(noticeNo);
    }

	@Override
	public void writeNotice(NoticeDto noticeDto) throws Exception {
		session.getMapper(NoticeRepository.class).writeNotice(noticeDto);
	}

	@Override
	public void updateNotice(int noticeNo, String noticeTitle, String noticeContent) throws Exception {
		session.getMapper(NoticeRepository.class).updateNotice(noticeNo, noticeTitle, noticeContent);
	}

	@Override
	public void deleteNotice(int noticeNo) throws Exception {
		session.getMapper(NoticeRepository.class).deleteNotice(noticeNo);
	}

	@Override
	public void hitNotice(int noticeNo) throws Exception {
		session.getMapper(NoticeRepository.class).hitNotice(noticeNo);
	}

	@Override
	public List<NoticeDto> getList(String key, String word) throws Exception {
		return session.getMapper(NoticeRepository.class).getList(key, word);
	}
}
