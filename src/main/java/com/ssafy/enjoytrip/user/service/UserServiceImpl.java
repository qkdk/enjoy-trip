package com.ssafy.enjoytrip.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private void isValidPassword(String userId, String userPw) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userId, userPw);
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    private static void isValidEmail(String emailDomain) {
        String EMAIL_PATTERN = "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        boolean result = pattern.matcher(emailDomain).matches();

        if (!result) {
            throw new RuntimeException("이메일 형식이 올바르지 않습니다.");
        }
    }

    @Override
    public int joinUser(JoinDto joinDto) {
        if (userRepository.getUserByUserId(joinDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }
        // 이메일 도메인 유효성 검사
        isValidEmail(joinDto.getUserDomain());
        joinDto.setUserPw(passwordEncoder.encode(joinDto.getUserPw()));
        return userRepository.joinUser(joinDto);
    }

    @Override
    public int idCheck(String userId) {
        return userRepository.idCheck(userId);
    }

    @Override
    public void modify(ModifyDto modifyDto) {
        String userId = "";
        Optional<String> currentUserId = SecurityUtil.getCurrentUserId();
        if (currentUserId.isPresent()) {
            userId = currentUserId.get();
        }

        isValidPassword(userId, modifyDto.getUserCurPw());
        if (StringUtils.hasText(modifyDto.getUserDomain())) {
            isValidEmail(modifyDto.getUserDomain());
        }

        modifyDto.setUserPw(passwordEncoder.encode(modifyDto.getUserPw()));
        Map map = objectMapper.convertValue(modifyDto, Map.class);
        map.put("userId", userId);
        userRepository.modify(map);
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
