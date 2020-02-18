package pl.detectivegame.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.DetectiveCase;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.DetectiveCaseResponse;
import pl.detectivegame.payload.UserProfile;
import pl.detectivegame.payload.UserSummary;
import pl.detectivegame.repository.DetectiveCaseRepository;
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
    DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    DetectiveCaseService detectiveCaseService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary =
                UserSummary.builder()
                        .id(currentUser.getId())
                        .username(currentUser.getUsername())
                        .name(currentUser.getName())
                        .build();
        return userSummary;
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long caseCount = detectiveCaseRepository.countByCreator(user.getId());

        UserProfile userProfile =
                UserProfile.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .joinedAt(user.getCreated())
                    .detectiveCaseCount(caseCount)
                    .build();

        return userProfile;
    }

    //TODO
//    @GetMapping("/users/{username}/detectiveCases")
//    public PagedResponse<DetectiveCaseResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
//                                                                  @CurrentUser UserPrincipal currentUser,
//                                                                  @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                                                  @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getPollsCreatedBy(username, currentUser, page, size);
//    }

}
