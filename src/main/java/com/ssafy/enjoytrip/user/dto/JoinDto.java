package com.ssafy.enjoytrip.user.dto;

import lombok.Data;

@Data
public class JoinDto {

    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userDomain;
}
