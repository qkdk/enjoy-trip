package com.ssafy.enjoytrip.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.user.dto.DeleteDto;
import com.ssafy.enjoytrip.user.dto.FindPwRequestDto;
import com.ssafy.enjoytrip.user.dto.FollowDto;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.dto.UserDetailDto;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.repository.UserRepository;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private Map<String, String> makeModifyMap(ModifyDto modifyDto, String userId) {
        Map<String, String> userMap = objectMapper.convertValue(userRepository.getUserByUserId(userId).get(),
                Map.class);
        if (StringUtils.hasText(modifyDto.getUserPw())) {
            modifyDto.setUserPw(passwordEncoder.encode(modifyDto.getUserPw()));
        }
        Map<String, String> modifyMap = objectMapper.convertValue(modifyDto, Map.class);

        for (Entry<String, String> entry : modifyMap.entrySet()) {
            if (!StringUtils.hasText(entry.getValue())) {
                modifyMap.put(entry.getKey(), userMap.get(entry.getKey()));
            }
        }

        return modifyMap;
    }

    @Override
    public UserDetailDto getUser(String userId) {
        Optional<UserDto> userByUserId = userRepository.getUserByUserId(userId);
        if (!userByUserId.isPresent()) {
            throw new RuntimeException("유저가 존재하지 않습니다.");
        }

        UserDto userDto = userByUserId.get();
        return UserDetailDto.builder()
                .userDomain(userDto.getUserDomain())
                .userEmail(userDto.getUserEmail())
                .userName(userDto.getUserName())
                .build();
    }

    @Override
    public int joinUser(JoinDto joinDto) {
        System.out.println("서비스까지 완료");
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

        Map<String, String> modifyMap = makeModifyMap(modifyDto, userId);

        modifyMap.put("userId", userId);
        userRepository.modify(modifyMap);
    }

    @Override
    public void deleteMember(DeleteDto deleteDto) {
        Optional<String> currentUserId = SecurityUtil.getCurrentUserId();
        if (currentUserId.isPresent()) {
            String userId = currentUserId.get();
            isValidPassword(userId, deleteDto.getUserPw());
            userRepository.deleteMember(userId);
        }
    }

    @Override
    public List<FollowDto> getFollowers(String userId) {
        return userRepository.getFollowers(userId);
    }

    @Override
    public void setFollowers(String userId, String follow_id) throws Exception {
        userRepository.setFollowers(userId, follow_id);
    }

    @Override
    public void delFollowers(String userId, String followId) throws Exception {
        userRepository.delFollowers(userId, followId);
    }

    @Override
    @Transactional
    public String findPw(FindPwRequestDto findPwRequestDto) {
        if (userRepository.getUserByFindPwRequestDto(
                        objectMapper.convertValue(findPwRequestDto, Map.class))
                .isPresent()) {
            // 랜덤한 난수 생성
        }
        return null;
    }

}
