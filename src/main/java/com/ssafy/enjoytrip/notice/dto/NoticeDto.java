package com.ssafy.enjoytrip.notice.dto;

import lombok.Data;

@Data
public class NoticeDto {

    String userId;
    int noticeNo;
    String noticeTitle;
    String noticeContent;
    String noticeCreateTime;
    int noticeHit;
}
