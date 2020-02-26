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
import pl.detectivegame.util.DetectiveCaseMapper;


@Slf4j
@Service
public class DetectiveCaseService {

    @Autowired
    private DetectiveCaseRepository detectiveCaseRepository;

    @Autowired
    private UserRepository userRepository;

    public DetectiveCaseResponse createDetectiveCase(DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCase detectiveCase =
                DetectiveCase.builder()
                        .id(detectiveCaseRequest.getId())
                        .image(detectiveCaseRequest.getImage())
                        .name(detectiveCaseRequest.getName())
                        .time(detectiveCaseRequest.getTime())
                        .description(detectiveCaseRequest.getDescription())
                        .ready(detectiveCaseRequest.isReady())
                        .build();
        detectiveCase = detectiveCaseRepository.save(detectiveCase);
        User creator = getCreator(detectiveCase);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCase, creator);
    }

    public DetectiveCaseResponse getDetectiveCaseById(Long detectiveCaseId) {
        DetectiveCase detectiveCase = detectiveCaseRepository.findById(detectiveCaseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", detectiveCaseId));
        User creator = getCreator(detectiveCase);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCase, creator);
    }

    private User getCreator(DetectiveCase detectiveCase) {
        return userRepository.findById(detectiveCase.getCreator())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", detectiveCase.getCreator()));
    }
}
