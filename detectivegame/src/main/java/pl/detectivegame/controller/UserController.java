package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.user.UserProfileResponse;
import pl.detectivegame.payload.user.UserSummaryResponse;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.security.CurrentUser;
import pl.detectivegame.security.UserPrincipal;
import pl.detectivegame.service.DetectiveCaseService;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummaryResponse userSummaryResponse =
                UserSummaryResponse.builder()
                        .id(currentUser.getId())
                        .username(currentUser.getUsername())
                        .name(currentUser.getName())
                        .build();
        return userSummaryResponse;
    }

    @GetMapping("/users/{username}")
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

    //TODO
//    @GetMapping("/users/{username}/detectiveCases")
//    public PagedResponse<DetectiveCaseInfoResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
//                                                                  @CurrentUser UserPrincipal currentUser,
//                                                                  @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                                                  @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getPollsCreatedBy(username, currentUser, page, size);
//    }

}
