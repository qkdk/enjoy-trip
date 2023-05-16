package com.ssafy.enjoytrip.plan.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
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
import org.springframework.web.bind.annotation.PathVariable;
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
        try {
            List<PlanDto> planDtos = planService.listPlan(pgno, key, word);
            ResponseTemplate<List<PlanDto>> body = ResponseTemplate.<List<PlanDto>>builder()
                    .msg("계획 읽기에 성공했습니다.")
                    .result(true)
                    .data(planDtos)
                    .build();
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            ResponseTemplate<List<PlanDto>> body = ResponseTemplate.<List<PlanDto>>builder()
                    .msg("계획 읽기에 실패했습니다.")
                    .result(false)
                    .build();
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view/{planNo}")
    public ResponseEntity<?> viewPlan(@PathVariable int planNo) {
        planService.viewPlan(planNo);
        return null;
    }
}
