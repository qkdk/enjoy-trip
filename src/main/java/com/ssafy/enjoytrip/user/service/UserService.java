package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.User;

public interface UserService {

    int joinUser(JoinDto joinDto);
	int idCheck(String userId);
	void modify(ModifyDto userDto);
	void deleteMember(String userId);
	List<String> getFollowers(String userId);
}
