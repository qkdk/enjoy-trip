package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.user.dto.DeleteDto;
import com.ssafy.enjoytrip.user.dto.FollowDto;
import com.ssafy.enjoytrip.user.dto.JoinDto;
import com.ssafy.enjoytrip.user.dto.ModifyDto;
import com.ssafy.enjoytrip.user.dto.UserDetailDto;
import com.ssafy.enjoytrip.user.service.UserService;
import com.ssafy.enjoytrip.util.ResponseTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/api")
public class RestUserController {

    UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }
    
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
        if(!file.isEmpty()) {
        	String saveFolder = uploadPath + File.separator;
        	File folder = new File(saveFolder);
        	if(!folder.exists()) folder.mkdirs();
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

    @PatchMapping("/modify")
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
//            List<FollowDto> followees = userService.getFollowers(userId);
//            ResponseTemplate<List<String>> body = ResponseTemplate.<List<String>>builder()
//                    .result(true)
//                    .msg("팔로우 불러오기가 성공했습니다.")
//                    .data(followees)
//                    .build();
//
//            return new ResponseEntity<>(body, HttpStatus.OK);
        	List<FollowDto> list = userService.getFollowers(userId);
        	for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getUserImgSrc() != null) {
					String base = "http://localhost:8080/enjoytrip/profile/" + list.get(i).getUserImgSrc();
					list.get(i).setUserImgSrc(base);
				}
			}
        	return new ResponseEntity<List<FollowDto>>(list,HttpStatus.OK);

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
}
