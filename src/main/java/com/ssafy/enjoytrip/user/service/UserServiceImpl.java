package com.ssafy.enjoytrip.user.service;

import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public int joinUser(JoinDto joinDto) {
        if (userRepository.getUserByUserId(joinDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }
        // 이메일 도메인 유효성 검사
        if (!isValidEmail(joinDto)) {
            throw new RuntimeException("이메일 형식이 올바르지 않습니다.");
        }
        joinDto.setUserPw(passwordEncoder.encode(joinDto.getUserPw()));
        return userRepository.joinUser(joinDto);
    }

    private static boolean isValidEmail(JoinDto joinDto) {
        String EMAIL_PATTERN = "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(joinDto.getUserDomain()).matches();
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
