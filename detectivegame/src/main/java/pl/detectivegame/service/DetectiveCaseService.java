package pl.detectivegame.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.DetectiveCase;
import pl.detectivegame.model.Save;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.*;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.SaveRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.util.DetectiveCaseMapper;

import java.util.Date;
import java.util.Optional;


@Slf4j
@Service
public class DetectiveCaseService {

    @Autowired
    private DetectiveCaseInfoRepository detectiveCaseInfoRepository;

    @Autowired
    SaveRepository saveRepository;

    @Autowired
    private UserRepository userRepository;

    public DetectiveCaseInfoResponse createDetectiveCase(DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCase detectiveCase =
                DetectiveCase.builder()
                        .id(detectiveCaseRequest.getId())
                        .image(detectiveCaseRequest.getImage())
                        .name(detectiveCaseRequest.getName())
                        .time(detectiveCaseRequest.getTime())
                        .description(detectiveCaseRequest.getDescription())
                        .ready(detectiveCaseRequest.isReady())
                        .build();
        detectiveCase = detectiveCaseInfoRepository.save(detectiveCase);
        User creator = getCreator(detectiveCase);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCase, creator);
    }

    public DetectiveCaseInfoResponse getDetectiveCaseInfoById(Long detectiveCaseId) {
        DetectiveCase detectiveCase = detectiveCaseInfoRepository.findById(detectiveCaseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", detectiveCaseId));
        User creator = getCreator(detectiveCase);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCase, creator);
    }

    private User getCreator(DetectiveCase detectiveCase) {
        return userRepository.findById(detectiveCase.getCreator())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", detectiveCase.getCreator()));
    }

    public DetectiveCaseResponse getNewDetectiveCaseById(Long detectiveCaseId) {
        //TODO implement
        return null;
    }


    public ResponseEntity<SaveDetectiveCaseResponse> saveDetectiveCase(JsonObject saveDetectiveCaseRequest) {

        String caseId;
        String player;
        String saveJson;
        try {
             caseId = saveDetectiveCaseRequest.get("caseId").toString();
             player = saveDetectiveCaseRequest.get("playerId").toString();
             saveJson = saveDetectiveCaseRequest.get("saveJson").toString();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Incorrect Request format or value");
        }


        Save save = Save.builder()
                .caseId(Long.parseLong(caseId))
                .player(Long.parseLong(player))
                .lastModified(new Date())
                .save_json(saveJson)
                .build();
        save = saveRepository.save(save);

        String saveId = save.getSave_id().toString();

        return ResponseEntity.ok(new SaveDetectiveCaseResponse(saveId));

    }

    public DetectiveCaseSaveResponse getDetectiveCaseSave(DetectiveCaseSaveRequest saveDetectiveCaseRequest) {

        Optional<Save> save = saveRepository.findFirstByPlayerAndCaseIdOrderByLastModifiedDesc(saveDetectiveCaseRequest.getUserId(),saveDetectiveCaseRequest.getCaseId());
        return DetectiveCaseSaveResponse.builder().jsonSave(save.map(o -> save.get().getSave_json()).orElse(null)).build();
    }
}
