package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.DAO.DetectiveCaseInfoWithCreator;
import pl.detectivegame.model.DAO.User;
import pl.detectivegame.payload.dashboard.AllDetectiveCasesResponse;
import pl.detectivegame.payload.user.UserProfileResponse;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.DetectiveCaseWithCreatorNameRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.service.DetectiveCaseService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @Autowired
    private DetectiveCaseWithCreatorNameRepository detectiveCaseWithCreatorNameRepository;


    @GetMapping("/{username}")
    public UserProfileResponse getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long caseCount = detectiveCaseInfoRepository.countByCreator(user.getId());

        UserProfileResponse userProfileResponse =
                UserProfileResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .joinedAt(user.getCreated())
                    .detectiveCaseCount(caseCount)
                    .build();

        return userProfileResponse;
    }

    @GetMapping("/createdDetectiveCases/{userId}")
    public AllDetectiveCasesResponse getAllCreatedDetectiveCasesForUser(@PathVariable(value = "userId") Long userId) {
        List<DetectiveCaseInfoWithCreator> saves = detectiveCaseWithCreatorNameRepository.findByCreator(userId);
        return AllDetectiveCasesResponse.builder().detectiveCaseList(saves).build();
    }

    @GetMapping("/activeDetectiveCases/{userId}")
    public AllDetectiveCasesResponse getAllActiveDetectiveCasesForUser(@PathVariable(value = "userId") Long userId) {
        List<DetectiveCaseInfoWithCreator> saves = detectiveCaseWithCreatorNameRepository.findByUserId(userId);
        return AllDetectiveCasesResponse.builder().detectiveCaseList(saves).build();
    }


}
