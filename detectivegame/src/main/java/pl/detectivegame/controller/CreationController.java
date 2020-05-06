package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.model.Action;
import pl.detectivegame.payload.ApiResponse;
import pl.detectivegame.payload.creation.*;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.*;

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

    @Autowired
    ItemService itemService;

    @Autowired
    PersonService personService;

    @Autowired
    ActionService actionService;

    @Autowired
    ValidationService validationService;

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createDetectiveCaseInfo")
    public DetectiveCaseInfoResponse createDetectiveCaseInfo(@Valid @RequestBody DetectiveCaseInfoRequest detectiveCaseInfoRequest) {
        DetectiveCaseInfoResponse detectiveCase = detectiveCaseService.createDetectiveCase(detectiveCaseInfoRequest);
        return detectiveCase;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateDetectiveCaseInfo")
    public DetectiveCaseInfoResponse updateDetectiveCaseInfo(@Valid @RequestBody DetectiveCaseInfoRequest detectiveCaseInfoRequest) throws Exception {
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


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createItem")
    public ItemPayload createItem(@Valid @RequestBody ItemPayload itemPayload) {
        return itemService.saveItem(itemPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateItem")
    public ItemPayload updateItem(@Valid @RequestBody ItemPayload itemPayload) {
        return itemService.saveItem(itemPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteItem")
    public ApiResponse deleteItem(@Valid @RequestBody ItemPayload itemPayload) {
        itemService.deleteItem(itemPayload);
        return new ApiResponse(true, "Item deleted");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createPerson")
    public PersonPayload createPerson(@Valid @RequestBody PersonPayload personPayload) {
        return personService.savePerson(personPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updatePerson")
    public PersonPayload updatePerson(@Valid @RequestBody PersonPayload personPayload) {
        return personService.savePerson(personPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deletePerson")
    public ApiResponse deletePerson(@Valid @RequestBody PersonPayload personPayload) {
        personService.deletePerson(personPayload);
        return new ApiResponse(true, "Person deleted");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/createAction")
    public ActionPayload createAction(@Valid @RequestBody ActionPayload actionPayload) {
        return actionService.createAction(actionPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/updateAction")
    public ActionPayload updateAction(@Valid @RequestBody ActionPayload actionPayload) {
        return actionService.updateAction(actionPayload);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteAction")
    public ApiResponse deleteAction(@Valid @RequestBody ActionPayload actionPayload) {
        actionService.deleteAction(actionPayload);
        return new ApiResponse(true, "Action deleted");
    }

    @GetMapping("/validate/{caseId}")
    public ValidatePayload validateDetectiveCase(@PathVariable(value = "caseId") Long caseId) {
        return validationService.validateDetectiveCase(caseId);
    }
}
