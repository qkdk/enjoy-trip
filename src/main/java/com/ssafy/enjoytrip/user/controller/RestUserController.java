package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.user.service.UserService;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class RestUserController {

    UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check/{userid}")
    public String idCheck(@PathVariable("userid") String userId) throws Exception {
        int cnt = userService.idCheck(userId);
        return cnt + "";
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        userService.joinUser(userDto);
        return new ResponseEntity<>("회원가입에 성공했습니다.", HttpStatus.OK);
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modify(@RequestBody UserDto userDto) {
        userService.modify(userDto);

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .result(true)
                        .msg("정보 수정에 성공했습니다.")
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, String> requestMap, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
        if (userDto.getUserPw().equals(requestMap.get("userPw"))) {
            try {
                userService.deleteMember(userDto.getUserId());
            } catch (Exception e) {
                return new ResponseEntity<>("삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
            } finally {
                session.invalidate();
            }
        } else {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("회원이 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable String userId) {
        try {
            List<String> followees = userService.getFollowers(userId);
            ResponseTemplate<List<String>> body = ResponseTemplate.<List<String>>builder()
                    .result(true)
                    .msg("팔로우 불러오기가 성공했습니다.")
                    .data(followees)
                    .build();

            return new ResponseEntity<>(body, HttpStatus.OK);

        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                System.out.println(element.toString());
            }
            ResponseTemplate<?> body = ResponseTemplate.builder()
                    .result(false)
                    .msg("팔로우 불러오기기 실패했습니다.")
                    .build();

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }
}
