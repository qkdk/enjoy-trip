package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private SqlSession session;

    @Autowired
    public UserServiceImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public UserDto loginUser(String id, String pw) throws SQLException {
        return session.getMapper(UserRepository.class).loginUser(id, pw);
    }

    @Override
    public int joinUser(String name, String id, String pw, String email, String emailDomain) throws SQLException {
        return session.getMapper(UserRepository.class).joinUser(name, id, pw, email, emailDomain);
    }

	@Override
	public int idCheck(String id) throws Exception {
		return session.getMapper(UserRepository.class).idCheck(id);
	}
}
