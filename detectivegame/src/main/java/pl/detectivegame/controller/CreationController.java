package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.ApiResponse;
import pl.detectivegame.payload.creation.LocationConnectionPayload;
import pl.detectivegame.payload.creation.DetectiveCaseInfoRequest;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.creation.LocationPayload;
import pl.detectivegame.payload.creation.QuestionPayload;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;
import pl.detectivegame.service.LocationConnectionService;
import pl.detectivegame.service.LocationService;
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

    @Autowired
    LocationService locationService;

    @Autowired
    LocationConnectionService locationConnectionService;

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

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createLocation")
    public @Valid LocationPayload createLocation(@Valid @RequestBody LocationPayload locationPayload) {
        return locationService.saveLocation(locationPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateLocation")
    public @Valid LocationPayload updateLocation(@Valid @RequestBody LocationPayload locationPayload) {
        return locationService.saveLocation(locationPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteLocation")
    public ApiResponse deleteLocation(@Valid @RequestBody LocationPayload locationPayload) {
        locationService.deleteLocation(locationPayload);
        return new ApiResponse(true, "Location deleted");
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteQuestion")
    public ApiResponse deleteQuestion(@Valid @RequestBody  QuestionPayload questionPayload) {
        questionService.deleteQuestion(questionPayload);
        return new ApiResponse(true, "Question deleted");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createLocationConnection")
    public @Valid LocationConnectionPayload createLocationConnection(@Valid @RequestBody LocationConnectionPayload locationConnectionPayload) {
        return locationConnectionService.saveLocationConnection(locationConnectionPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateLocationConnection")
    public @Valid LocationConnectionPayload updateLocationConnection(@Valid @RequestBody LocationConnectionPayload locationConnectionPayload) {
        return locationConnectionService.saveLocationConnection(locationConnectionPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteLocationConnection")
    public ApiResponse deleteLocationConnection(@Valid @RequestBody LocationConnectionPayload locationConnectionPayload) {
        locationConnectionService.deleteLocationConnection(locationConnectionPayload);
        return new ApiResponse(true, "LocationConnection deleted");
    }

}
