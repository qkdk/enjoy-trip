package com.ssafy.enjoytrip.trail.dto;

import lombok.Data;

@Data
public class TrailInputDto {

	private int trail_id;
    private String title;   // 길명
    private String description;    // 길소개
    private String total_length;     // 총길이
    private String estimated_time;    // 총소요시간
    private String start_name;
    private int start_sido_code;
    private int start_gugun_code;
    private String start_detail_addr;
    private String end_name;
    private int end_sido_code;
    private int end_gugun_code;
    private String end_detail_addr;
    private String route;
    private String tel;
    private String manage_name;
    private String add_date;
    private String provide_code;
    private String provide_name;
}
