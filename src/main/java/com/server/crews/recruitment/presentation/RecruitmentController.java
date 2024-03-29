package com.server.crews.recruitment.presentation;

import com.server.crews.auth.presentation.Authentication;
import com.server.crews.recruitment.application.RecruitmentService;
import com.server.crews.recruitment.domain.Recruitment;
import com.server.crews.recruitment.dto.request.DeadlineUpdateRequest;
import com.server.crews.recruitment.dto.request.ProgressStateUpdateRequest;
import com.server.crews.recruitment.dto.request.RecruitmentSaveRequest;
import com.server.crews.recruitment.dto.response.RecruitmentDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recruitments")
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping
    @Operation(description = "지원서 양식을 저장한다.")
    public ResponseEntity<Void> saveRecruitment(
            @Authentication final Recruitment accessedRecruitment,
            @RequestBody final RecruitmentSaveRequest request) {
        recruitmentService.saveRecruitment(accessedRecruitment, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/progress")
    @Operation(description = "모집 상태를 변경한다.")
    public ResponseEntity<Void> updateProgressState(
            @Authentication final Recruitment accessedRecruitment,
            @RequestBody final ProgressStateUpdateRequest request) {
        recruitmentService.updateProgressState(accessedRecruitment, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{recruitment-id}")
    @Operation(description = "지원서 양식 상세 정보를 조회한다.")
    public ResponseEntity<RecruitmentDetailsResponse> getRecruitmentDetails(
            @PathVariable(value = "recruitment-id") final String recruitmentId) {
        return ResponseEntity.ok(recruitmentService.getRecruitmentDetails(recruitmentId));
    }

    @PatchMapping("/deadline")
    @Operation(description = "지원서 양식의 마감기한을 변경한다.")
    public ResponseEntity<Void> updateProgressState(
            @Authentication final Recruitment accessedRecruitment,
            @RequestBody final DeadlineUpdateRequest request) {
        recruitmentService.updateDeadline(accessedRecruitment, request);
        return ResponseEntity.ok().build();
    }
}
