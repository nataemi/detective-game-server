package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.creation.DetectiveCaseInfoRequest;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.creation.QuestionPayload;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;
import pl.detectivegame.service.QuestionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/create")
@Slf4j
public class CreationController {

    @Autowired
    DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @Autowired
    QuestionService questionService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createDetectiveCaseInfo")
    public DetectiveCaseInfoResponse createDetectiveCaseInfo(@Valid @RequestBody DetectiveCaseInfoRequest detectiveCaseInfoRequest) {
        DetectiveCaseInfoResponse detectiveCase = detectiveCaseService.createDetectiveCase(detectiveCaseInfoRequest);
        return detectiveCase;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateDetectiveCaseInfo")
    public DetectiveCaseInfoResponse updateDetectiveCaseInfo(@Valid @RequestBody DetectiveCaseInfoRequest detectiveCaseInfoRequest) {
        DetectiveCaseInfoResponse detectiveCase = detectiveCaseService.updateDetectiveCase(detectiveCaseInfoRequest);
        return detectiveCase;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createQuestion")
    public QuestionPayload createQuestion(@Valid @RequestBody QuestionPayload questionPayload) {
        return questionService.createQuestion(questionPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateQuestion")
    public QuestionPayload updateQuestion(@Valid @RequestBody QuestionPayload questionPayload) {
        return questionService.updateQuestion(questionPayload);
    }


}
