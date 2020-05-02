package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.creation.DetectiveCaseInfoRequest;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;

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
    @PutMapping("/createDetectiveCaseInfo")
    public DetectiveCaseInfoResponse createQuestion(@Valid @RequestBody DetectiveCaseInfoRequest detectiveCaseInfoRequest) {
        DetectiveCaseInfoResponse detectiveCase = detectiveCaseService.createDetectiveCase(detectiveCaseInfoRequest);
        return detectiveCase;
    }
}
