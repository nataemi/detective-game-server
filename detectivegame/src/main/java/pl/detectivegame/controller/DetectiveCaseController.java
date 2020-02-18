package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.detectivegame.model.DetectiveCase;
import pl.detectivegame.payload.ApiResponse;
import pl.detectivegame.payload.DetectiveCaseRequest;
import pl.detectivegame.payload.DetectiveCaseResponse;
import pl.detectivegame.repository.DetectiveCaseRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.security.CurrentUser;
import pl.detectivegame.security.UserPrincipal;
import pl.detectivegame.service.DetectiveCaseService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/case")
@Slf4j
public class DetectiveCaseController {

    @Autowired
    DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createDetectiveCase(@Valid @RequestBody DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCase detectiveCase = detectiveCaseService.createDetectiveCase(detectiveCaseRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{detectiveCaseId}")
                .buildAndExpand(detectiveCase.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Detective case created Successfully"));
    }

    @GetMapping("/{detectiveCaseId}")
    public DetectiveCaseResponse getDetectiveCaseById(@CurrentUser UserPrincipal currentUser,
                                             @PathVariable Long detectiveCaseId) {
        return detectiveCaseService.getDetectiveCaseById(detectiveCaseId, currentUser);
    }
}
