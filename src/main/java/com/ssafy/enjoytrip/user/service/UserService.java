package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.User;

public interface UserService {

    int joinUser(UserDto userDto);
	int idCheck(String userId);
	void modify(UserDto userDto);
	void deleteMember(String userId);
	List<String> getFollowers(String userId);
}
