package com.ssafy.enjoytrip.user.dto;

import lombok.Data;

@Data
public class ModifyDto {
    private String userPw;
    private String userName;
    private String userEmail;
    private String userDomain;
    private String userCurPw;
    private String userImgSrc;
}
