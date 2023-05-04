package com.ssafy.enjoytrip.place.dto;

import lombok.Data;

@Data
public class ReplyDto {

	int replyId;
	String replyContent;
	String replyCreateTime;
	String userId;
	int placeNo;
	int parentId;
}
