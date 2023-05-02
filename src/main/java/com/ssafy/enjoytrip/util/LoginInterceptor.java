package com.ssafy.enjoytrip.util;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.user.dto.UserDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());

        if (userDto == null) {
            request.setAttribute(LoginConstant.LOGIN_MESSAGE_KEY.getValue(),
                    LoginConstant.LOGIN_NEED_MESSAGE.getValue());
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        return true;
    }
}
