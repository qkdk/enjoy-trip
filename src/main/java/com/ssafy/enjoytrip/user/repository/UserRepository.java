package com.ssafy.enjoytrip.user.repository;

import com.ssafy.enjoytrip.user.dto.UserDto;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    UserDto loginUser(@Param("user_id") String id, @Param("user_pw") String pw) throws SQLException;

    int joinUser(@Param("user_name") String name, @Param("user_id") String id, @Param("user_pw") String pw,
            @Param("user_email") String email, @Param("user_domain") String emailDomain) throws SQLException;

}
