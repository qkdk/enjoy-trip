package com.ssafy.enjoytrip.user.repository;

import com.ssafy.enjoytrip.user.dto.FindPwRequestDto;
import com.ssafy.enjoytrip.user.dto.FollowDto;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    Optional<UserDto> getUserByUserId(String userId);

    int joinUser(JoinDto joinDto);

    int idCheck(String userId);

    void modify(Map<String, String> map);

    void deleteMember(String userId);

    List<FollowDto> getFollowers(String userId);

    void setFollowers(String userId, String follow_id) throws Exception;

    void delFollowers(String userId, String followId) throws Exception;

    Optional<UserDto> getUserByFindPwRequestDto(Map map);
}
