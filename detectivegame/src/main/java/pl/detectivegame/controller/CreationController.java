package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.DetectiveCaseRequest;
import pl.detectivegame.payload.DetectiveCaseResponse;
import pl.detectivegame.repository.DetectiveCaseRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/create")
@Slf4j
public class CreationController {

    @Autowired
    DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/createDetectiveCase")
    public DetectiveCaseResponse createDetectiveCase(@Valid @RequestBody DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCaseResponse detectiveCase = detectiveCaseService.createDetectiveCase(detectiveCaseRequest);
        return detectiveCase;
    }
}
