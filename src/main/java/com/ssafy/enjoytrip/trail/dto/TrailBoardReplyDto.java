package com.ssafy.enjoytrip.trail.dto;

import lombok.Data;

@Data
public class TrailBoardReplyDto {

	int replyNo;
	int boardNo;
	String replyUserId;
	String replyContent;
	String replyCreateTime;
}
