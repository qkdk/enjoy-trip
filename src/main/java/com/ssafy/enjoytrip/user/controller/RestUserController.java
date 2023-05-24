package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.user.dto.DeleteDto;
import com.ssafy.enjoytrip.user.dto.FindPwRequestDto;
import com.ssafy.enjoytrip.user.dto.FollowDto;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.dto.UserDetailDto;
import com.ssafy.enjoytrip.user.service.UserService;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import java.io.File;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/user/api")
@RequiredArgsConstructor
@RestController
public class RestUserController {

    private final UserService userService;

    @Value("${profile.path}")
    private String uploadPath;

    @GetMapping("/check/{userid}")
    public String idCheck(@PathVariable("userid") String userId) throws Exception {
        int cnt = userService.idCheck(userId);
        return cnt + "";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseTemplate<UserDetailDto>> getUser(@PathVariable String userId) {
        UserDetailDto userDetail = userService.getUser(userId);
        return new ResponseEntity<>(
                ResponseTemplate.<UserDetailDto>builder()
                        .msg("유저 블러오기 성공")
                        .result(true)
                        .data(userDetail)
                        .build()
                , HttpStatus.OK
        );

    }

    @PostMapping("/join")
    public ResponseEntity<ResponseTemplate> join(@RequestParam("file") MultipartFile file, String userId, String userPw,
            String userName, String userEmail, String userDomain) throws Exception {
        JoinDto joinDto = new JoinDto();
        joinDto.setUserId(userId);
        joinDto.setUserPw(userPw);
        joinDto.setUserName(userName);
        joinDto.setUserEmail(userEmail);
        joinDto.setUserDomain(userDomain);
        if (!file.isEmpty()) {
            String saveFolder = uploadPath + File.separator;
            File folder = new File(saveFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString()
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            joinDto.setUserImgSrc(saveFileName);
            file.transferTo(new File(folder, saveFileName));
        }
        System.out.println(joinDto);
        userService.joinUser(joinDto);

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .result(true)
                        .msg("회원가입에 성공하였습니다.")
                        .build(),
                HttpStatus.OK);
    }

    @PutMapping("/modify/profile")
    public ResponseEntity<ResponseTemplate> modifyProfile(@RequestParam("file") MultipartFile file, String userPw,
            String userName, String userEmail, String userDomain, String userCurPw) throws Exception {
        ModifyDto modifyDto = new ModifyDto();
        modifyDto.setUserCurPw(userCurPw);
        modifyDto.setUserDomain(userDomain);
        modifyDto.setUserEmail(userEmail);
        modifyDto.setUserName(userName);
        modifyDto.setUserPw(userPw);
        if (!file.isEmpty()) {
            String saveFolder = uploadPath + File.separator;
            File folder = new File(saveFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String originalFileName = file.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString()
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            modifyDto.setUserImgSrc(saveFileName);
            file.transferTo(new File(folder, saveFileName));
        }
        userService.modify(modifyDto);

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .result(true)
                        .msg("정보 수정에 성공했습니다.")
                        .build(),
                HttpStatus.OK);

    }

    @PutMapping("/modify")
    public ResponseEntity<ResponseTemplate> modify(@RequestBody ModifyDto modifyDto) {
        userService.modify(modifyDto);

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .result(true)
                        .msg("정보 수정에 성공했습니다.")
                        .build(),
                HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate> delete(@RequestBody DeleteDto deleteDto) {
        userService.deleteMember(deleteDto);
        return new ResponseEntity<>(ResponseTemplate.builder()
                .result(true)
                .msg("삭제에 성공하였습니다.")
                .build(),
                HttpStatus.OK);

    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<FollowDto>> getFollowers(@PathVariable String userId) {
        try {
            List<FollowDto> list = userService.getFollowers(userId);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserImgSrc() != null) {
                    String base = "/profile/" + list.get(i).getUserImgSrc();
                    list.get(i).setUserImgSrc(base);
                }
            }
            return new ResponseEntity<List<FollowDto>>(list, HttpStatus.OK);

        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                System.out.println(element.toString());
            }
            ResponseTemplate<?> body = ResponseTemplate.builder()
                    .result(false)
                    .msg("팔로우 불러오기기 실패했습니다.")
                    .build();

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/followers/{userId}/{follow_id}")
    public ResponseEntity<String> setFollowers(@PathVariable String userId, @PathVariable String follow_id)
            throws Exception {
        userService.setFollowers(userId, follow_id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping("/followers/del/{userId}/{followId}")
    public ResponseEntity<String> delFollowers(@PathVariable String userId, @PathVariable String followId)
            throws Exception {
        userService.delFollowers(userId, followId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<?> findPw(@RequestBody FindPwRequestDto findPwRequestDto) {
        System.out.println("cc");
        // requestDto랑 같은 값을 가지는 데이터가 있는지 확인
        // 같은 계정이 있다면 랜덤 한 난수 함수 생성
        // 랜덤한 난수를 인코딩 해서 db에 삽입
        // 난수를 메일로 전송

        userService.findPw(findPwRequestDto);
        return null;
    }
}
