package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check/{userid}")
    @ResponseBody
    public String idCheck(@PathVariable("userid") String userId) throws Exception {
        int cnt = userService.idCheck(userId);
        return cnt + "";
    }

//    @PostMapping("/login")
//    public String login(String id, String pw, Model model, HttpSession session) throws Exception {
//        UserDto userDto = userService.loginUser(id, pw);
//        if (userDto == null) {
//            model.addAttribute(LoginConstant.LOGIN_MESSAGE_KEY.getValue(), LoginConstant.LOGIN_NOT_MATCH.getValue());
//        } else {
//            session.setAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue(), userDto);
//        }
//        return "redirect:/";
//    }

//    @PostMapping("/join")
//    public String join(String name, String id, String password, String passwordCheck, String email,
//            @RequestParam("email_domain") String emailDomain, Model model)
//            throws Exception {
//
//        if (password.equals(passwordCheck)) {
//            userService.joinUser(name, id, password, email, emailDomain);
//        } else {
//            model.addAttribute(LoginConstant.LOGIN_MESSAGE_KEY.getValue(), LoginConstant.LOGIN_NOT_MATCH.getValue());
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public ModelAndView mypage(ModelAndView mv, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
        mv.addObject(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue(), userDto);
        mv.setViewName("mypage");
        return mv;
    }

//    @PostMapping("/modify")
//    public String modify(HttpSession session, UserDto userDto) throws Exception {
//        session.invalidate();
//        userService.modify(userDto);
//        return "redirect:/";
//    }

//    @PostMapping("/delete")
//    public String delete(String userPw, HttpSession session) throws Exception {
//        UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
//        if (userDto.getUserPw().equals(userPw)) {
//            userService.deleteMember(userDto.getUserId());
//        }
//        session.invalidate();
//        return "redirect:/";
//    }

}
