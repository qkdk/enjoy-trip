package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.DeleteDto;
import com.ssafy.enjoytrip.user.dto.FollowDto;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.dto.UserDetailDto;
import java.util.List;

public interface UserService {

    UserDetailDto getUser(String userId);

    int joinUser(JoinDto joinDto);

    int idCheck(String userId);

    void modify(ModifyDto userDto);

    void deleteMember(DeleteDto deleteDto);

    List<FollowDto> getFollowers(String userId);
}
