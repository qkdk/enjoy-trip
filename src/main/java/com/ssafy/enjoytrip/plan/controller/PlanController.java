package com.ssafy.enjoytrip.plan.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.plan.dto.PlanListResponseDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.service.PlanService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/write")
    public ResponseEntity<?> writePlan(@RequestBody PlanWriteRequestDto planWriteRequestDto, HttpSession session) {
        // 세션방식
        try {
            UserDto userDto = (UserDto) session.getAttribute(LoginConstant.LOGIN_ATTRIBUTE_NAME.getValue());
            planService.writePlan(planWriteRequestDto, userDto.getUserId());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<?> listPlan(int pgno, String key, String word) {
        System.out.println("ct");
        List<PlanListResponseDto> planListResponseDtos = planService.listPlan(pgno, key, word);
        System.out.println(planListResponseDtos);
        ResponseTemplate<List<PlanListResponseDto>> body = ResponseTemplate.<List<PlanListResponseDto>>builder()
                .msg("읽기 성공")
                .result(true)
                .data(planListResponseDtos)
                .build();
        System.out.println(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
