package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.DetectiveCaseResponse;
import pl.detectivegame.repository.DetectiveCaseRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;

@RestController
@RequestMapping("/api/play")
@Slf4j
public class GameplayController {

    @Autowired
    DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @GetMapping("/getDetectiveCaseInfoById/{detectiveCaseId}")
    public DetectiveCaseResponse getDetectiveCaseInfoById(@PathVariable Long detectiveCaseId) {
        return detectiveCaseService.getDetectiveCaseById(detectiveCaseId);
    }
}
