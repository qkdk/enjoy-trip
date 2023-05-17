package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.user.dto.LoginDto;
import com.ssafy.enjoytrip.user.dto.LoginResponseDto;
import com.ssafy.enjoytrip.user.service.UserService;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import com.ssafy.enjoytrip.util.jwt.JwtFilter;
import com.ssafy.enjoytrip.util.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authorize(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getUserPw());

        // CustomUserDetailsService의 override 함수가 실행되고
        // 이후에 검사를 실시함
        // 오류가 나타나면 서비스단에서 오류 발생시킴 걱정
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String ss = authorities.stream().findFirst().get().toString();
     
        System.out.println(ss);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        UserResponseDto userResponseDto = null;
//        userResponseDto.setAccessToken(jwt);
//        userResponseDto.setUserRole(authorities.getClass().getName());
        return new ResponseEntity<>(
                ResponseTemplate.<LoginResponseDto>builder()
                        .result(true)
                        .msg("로그인에 성공헸습니다")
                        .data(new LoginResponseDto(jwt, ss, loginDto.getUserId()))
                        .build()
                , httpHeaders, HttpStatus.OK);
    }
}
