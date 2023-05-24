package com.ssafy.enjoytrip.user.dto;

import lombok.Data;

@Data
public class FindPwRequestDto {
    private String userId;
    private String userEmail;
    private String emailDomain;
}
