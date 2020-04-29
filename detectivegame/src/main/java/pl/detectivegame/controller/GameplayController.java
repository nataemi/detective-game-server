package pl.detectivegame.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.detectivegame.payload.gameplay.*;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/play")
@Slf4j
public class GameplayController {

    @Autowired
    DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @GetMapping("/getDetectiveCaseInfoById/{detectiveCaseId}")
    public DetectiveCaseInfoResponse getDetectiveCaseInfoById(@PathVariable Long detectiveCaseId) {
        return detectiveCaseService.getDetectiveCaseInfoById(detectiveCaseId);
    }

    @GetMapping("/getNewDetectiveCaseById/{detectiveCaseId}")
    public DetectiveCaseResponse getDetectiveCaseById(@PathVariable Long detectiveCaseId) {
        return detectiveCaseService.getNewDetectiveCaseById(detectiveCaseId);
    }

    @RequestMapping(value = "/saveDetectiveCase", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<SaveDetectiveCaseResponse> saveDetectiveCase(@RequestBody String saveDetectiveCaseRequest) {
        Gson gson = new Gson();
        JsonObject jsonSaveDetectiveCaseRequest = gson.fromJson(saveDetectiveCaseRequest, JsonObject.class);
        ResponseEntity<SaveDetectiveCaseResponse> save = detectiveCaseService.saveDetectiveCase(jsonSaveDetectiveCaseRequest);
        return save;
    }

    @RequestMapping(value = "/getDetectiveCaseSave", method = RequestMethod.POST)
    public DetectiveCaseSaveResponse getDetectiveCaseSave(@Valid @RequestBody DetectiveCaseSaveRequest saveDetectiveCaseRequest) {
        DetectiveCaseSaveResponse save = detectiveCaseService.getDetectiveCaseSave(saveDetectiveCaseRequest);
        return save;
    }
}
