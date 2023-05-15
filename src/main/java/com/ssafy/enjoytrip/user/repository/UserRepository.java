package com.ssafy.enjoytrip.user.repository;

import com.ssafy.enjoytrip.user.dto.UserDto;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    UserDto loginUser(@Param("user_id") String id, @Param("user_pw") String pw) throws SQLException;

    int joinUser(@Param("user_name") String name, @Param("user_id") String id, @Param("user_pw") String pw,
            @Param("user_email") String email, @Param("user_domain") String emailDomain) throws SQLException;
    int idCheck(String userId) throws Exception;
    
    void modify(UserDto userDto) throws Exception;
    
    void deleteMember(String userId) throws Exception;

    List<String> getFollowers(String userId);
}
