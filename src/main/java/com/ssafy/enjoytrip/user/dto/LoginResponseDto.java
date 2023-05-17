package com.ssafy.enjoytrip.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

	private String accessToken;
    private String userRole;
    private String userId;
}
