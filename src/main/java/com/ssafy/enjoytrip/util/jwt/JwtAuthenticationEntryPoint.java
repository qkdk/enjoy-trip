package com.ssafy.enjoytrip.util.jwt;

import com.ssafy.enjoytrip.util.CustomException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
//        response.getWriter().write(("AuthenticationEntryPoint"));
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인 필요");
    }

}
