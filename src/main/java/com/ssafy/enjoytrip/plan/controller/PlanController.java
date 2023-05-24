package com.ssafy.enjoytrip.plan.controller;

import com.ssafy.enjoytrip.plan.dto.PlanDetailDto;
import com.ssafy.enjoytrip.plan.dto.PlanDto;
import com.ssafy.enjoytrip.plan.dto.PlanWriteRequestDto;
import com.ssafy.enjoytrip.plan.service.PlanService;
import com.ssafy.enjoytrip.util.ResponseTemplate;
import com.ssafy.enjoytrip.util.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping("/write")
    public ResponseEntity<ResponseTemplate<?>> writePlan(@RequestBody PlanWriteRequestDto planWriteRequestDto) {
        planService.writePlan(planWriteRequestDto, SecurityUtil.getCurrentUserId().get());

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .msg("계획쓰기에 성공했습니다.")
                        .result(true)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/view")
    public ResponseEntity<ResponseTemplate<List<PlanDto>>> listPlan(int pgno, String key, String word, String order) {
        List<PlanDto> planDtos = planService.listPlan(pgno, key, word, order);

        return new ResponseEntity<>(
                ResponseTemplate.<List<PlanDto>>builder()
                        .msg("계획 읽기에 성공했습니다.")
                        .result(true)
                        .data(planDtos)
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<ResponseTemplate<PlanDetailDto>> viewPlan(@PathVariable int planId) {
        PlanDetailDto planDetailDto = planService.viewPlan(planId);

        return new ResponseEntity<>(
                ResponseTemplate.<PlanDetailDto>builder()
                        .result(true)
                        .msg("계획 상세조회에 성공했습니다.")
                        .data(planDetailDto)
                        .build()
                , HttpStatus.OK);
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlanRecommend(@PathVariable int planId, @RequestParam int value) {
        planService.updatePlanRecommend(planId, SecurityUtil.getCurrentUserId().get(), value);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // 내 계획으로 가져오기
    @GetMapping("/copy/{planId}")
    public ResponseEntity<ResponseTemplate<?>> copyPlan(@PathVariable int planId) {
        planService.copyPlan(planId, SecurityUtil.getCurrentUserId().get());

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .result(true)
                        .msg("가져오기 성공")
                        .build(),
                HttpStatus.OK
        );
    }

    // 내 계획 보기
    @GetMapping("/view/user")
    public ResponseEntity<ResponseTemplate<List<PlanDto>>> listUserPlan() {
        return new ResponseEntity<>(
                ResponseTemplate.<List<PlanDto>>builder()
                        .msg("계획 읽기에 성공했습니다.")
                        .result(true)
                        .data(planService.listUserPlan(SecurityUtil.getCurrentUserId().get()))
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<ResponseTemplate<?>> deletePlan(@PathVariable int planId) {
        planService.deletePlan(planId, SecurityUtil.getCurrentUserId().get());

        return new ResponseEntity<>(
                ResponseTemplate.builder()
                        .msg("삭제성공")
                        .result(true)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/recommend")
    public ResponseEntity<ResponseTemplate<List<String>>> recommendPlanList() {
        return new ResponseEntity<>(
                ResponseTemplate.<List<String>>builder()
                        .result(true)
                        .msg("좋아요 리스트 불러오기 성공")
                        .data(planService.getRecommendList(SecurityUtil.getCurrentUserId().get()))
                        .build(),
                HttpStatus.OK
        );
    }
}
