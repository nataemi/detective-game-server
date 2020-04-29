package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.detectivegame.payload.dashboard.AllDetectiveCasesResponse;
import pl.detectivegame.service.DetectiveCaseService;

@RestController
@RequestMapping("/api/dashboard")
@Slf4j
public class DashboardController {

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @GetMapping("/getAllCases")
    public AllDetectiveCasesResponse getAllDetectiveCases() {
        return detectiveCaseService.getAllDetectiveCases();
    }

}
