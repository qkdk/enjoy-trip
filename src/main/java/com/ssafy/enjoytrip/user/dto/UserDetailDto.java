package com.ssafy.enjoytrip.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailDto {

    private String userEmail;
    private String userDomain;
    private String userName;
}
