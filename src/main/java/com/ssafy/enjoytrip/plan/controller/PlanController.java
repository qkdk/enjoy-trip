package com.ssafy.enjoytrip.plan.controller;

import com.ssafy.enjoytrip.enums.LoginConstant;
import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.service.PlanService;
import com.ssafy.enjoytrip.user.dto.UserDto;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

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
    public ResponseEntity<ResponseTemplate<List<PlanDto>>> listPlan(int pgno, String key, String word, String order) {
        List<PlanDto> planDtos = planService.listPlan(pgno, key, word, order);

        return new ResponseEntity<>(ResponseTemplate.<List<PlanDto>>builder()
                .msg("계획 읽기에 성공했습니다.")
                .result(true)
                .data(planDtos)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<ResponseTemplate<PlanDetailDto>> viewPlan(@PathVariable int planId) {
        PlanDetailDto planDetailDto = planService.viewPlan(planId);

        return new ResponseEntity<>(ResponseTemplate.<PlanDetailDto>builder()
                .result(true)
                .msg("계획 상세조회에 성공했습니다.")
                .data(planDetailDto)
                .build()
                , HttpStatus.OK);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlanRecommend(@PathVariable int planId) {
        planService.updatePlanRecommend(planId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
