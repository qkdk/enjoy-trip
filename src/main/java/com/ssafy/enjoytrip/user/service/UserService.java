package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.UserDto;
import java.sql.SQLException;

public interface UserService {

    UserDto loginUser(String id, String pw) throws Exception;

    int joinUser(String name, String id, String pw, String email, String emailDomain) throws Exception;
    
	int idCheck(String userId) throws Exception;
	
	void modify(UserDto userDto) throws Exception;
	
	void deleteMember(String userId) throws Exception;
}
