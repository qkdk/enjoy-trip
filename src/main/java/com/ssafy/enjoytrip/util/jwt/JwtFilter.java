package com.ssafy.enjoytrip.util.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    //해더의 키값 Authorization: Barer ......
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 자동으로 빈 등록 됨
    // RequiredArgsConstructor
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        // StringUtils.hasText -> null과 공백이 아닌지 검사하는 매서드
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.info("Security Context에 '{}' 인증 정보를 저장했습니다., uri:{}", authentication.getName(), requestURI);
        } else {
            LOGGER.info("유효한 JWT 토큰이 없습니다., uri:{}", requestURI);
        }
        // 다음 필터를 홀출
        // 서블릿 컨테이너에서 생성되고 관리되는 객체, FilterChain
        // 개발자가 지정해주고
        // 다음 필터를 서블릿 컨테이너에서 결정
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    // 해더에서 배어러를 뺀 jwt 만 뽑음
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
