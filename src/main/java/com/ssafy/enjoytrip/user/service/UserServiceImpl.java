package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
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
    public UserDto loginUser(String id, String pw) throws Exception {
        return session.getMapper(UserRepository.class).loginUser(id, pw);
    }

    @Override
    public int joinUser(String name, String id, String pw, String email, String emailDomain) throws Exception {
        return session.getMapper(UserRepository.class).joinUser(name, id, pw, email, emailDomain);
    }

	@Override
	public int idCheck(String userId) throws Exception {
		return session.getMapper(UserRepository.class).idCheck(userId);
	}

	@Override
	public void modify(UserDto userDto) throws Exception {
		session.getMapper(UserRepository.class).modify(userDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		session.getMapper(UserRepository.class).deleteMember(userId);
	}

    @Override
    public List<String> getFollowers(String userId) {
        return session.getMapper(UserRepository.class).getFollowers(userId);
    }
}
