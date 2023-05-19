package com.ssafy.enjoytrip.place.dto;

import lombok.Data;

@Data
public class PlaceDto {
	
	String userId;
	int placeNo;
	String placeTitle;
	String placeContent;
	int hit;
	int recommend;
	String placeImgSrc;
	String placeCreateTime;
}
