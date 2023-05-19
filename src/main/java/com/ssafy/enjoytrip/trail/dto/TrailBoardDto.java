package com.ssafy.enjoytrip.trail.dto;

import lombok.Data;

@Data
public class TrailBoardDto {
	private int trail_board_no;
	private int trail_board_trail_id;
	private String user_id;
	private String trail_board_title;
	private String trail_board_content;
	private String trail_board_start_time;
	private String trail_board_end_time;
	private String trail_board_create_time;
	private String trail_party_member_id;
	private int trail_board_member_count;
	private int trail_board_max_member;
	
}
