package pl.detectivegame.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.DetectiveCase;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.DetectiveCaseRequest;
import pl.detectivegame.payload.DetectiveCaseResponse;
import pl.detectivegame.repository.DetectiveCaseRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.security.UserPrincipal;
import pl.detectivegame.util.DetectiveCaseMapper;


@Slf4j
@Service
public class DetectiveCaseService {

    @Autowired
    private DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    private UserRepository userRepository;

    public DetectiveCase createDetectiveCase(DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCase detectiveCase =
                DetectiveCase.builder()
                        .image(detectiveCaseRequest.getImage())
                        .name(detectiveCaseRequest.getName())
                        .time(detectiveCaseRequest.getTime())
                        .description(detectiveCaseRequest.getDescription())
                        .ready(detectiveCaseRequest.isReady())
                        .build();

        return detectiveCaseRepository.save(detectiveCase);
    }

    public DetectiveCaseResponse getDetectiveCaseById(Long detectiveCaseId, UserPrincipal currentUser) {
        DetectiveCase detectiveCase = detectiveCaseRepository.findById(detectiveCaseId).orElseThrow(
                () -> new ResourceNotFoundException("Poll", "id", detectiveCaseId));


        // Retrieve poll creator details
        User creator = userRepository.findById(detectiveCase.getCreator())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", detectiveCase.getCreator()));


        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseRespone(detectiveCase, creator);
    }
}
