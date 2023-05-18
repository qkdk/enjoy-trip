package com.ssafy.enjoytrip.user.repository;

import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    Optional<UserDto> getUserByUserId(String userId);

//    int joinUser(@Param("user_name") String name, @Param("user_id") String id, @Param("user_pw") String pw,
//            @Param("user_email") String email, @Param("user_domain") String emailDomain);

    int joinUser(JoinDto joinDto);
    int idCheck(String userId);
    
    void modify(UserDto userDto);
    
    void deleteMember(String userId);

    List<String> getFollowers(String userId);
}
