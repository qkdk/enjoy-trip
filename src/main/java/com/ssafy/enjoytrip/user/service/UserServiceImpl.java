package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public int joinUser(UserDto userDto) {
        if (userRepository.getUserByUserId(userDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        return userRepository.joinUser(userDto);
    }

    @Override
    public int idCheck(String userId) {
        return userRepository.idCheck(userId);
    }

    @Override
    public void modify(UserDto userDto) {
        Optional<String> currentUserId = SecurityUtil.getCurrentUserId();
        currentUserId.ifPresent(userDto::setUserId);

        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        userRepository.modify(userDto);
    }

    @Override
    public void deleteMember(String userId) {
        userRepository.deleteMember(userId);
    }

    @Override
    public List<String> getFollowers(String userId) {
        return userRepository.getFollowers(userId);
    }
}
