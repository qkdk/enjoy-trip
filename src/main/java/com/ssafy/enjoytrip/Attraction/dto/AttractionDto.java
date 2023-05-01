package com.ssafy.enjoytrip.Attraction.dto;

import lombok.Data;

@Data
public class AttractionDto {

    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipCode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mLevel;
}
