package pl.detectivegame.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.detectivegame.exception.ResourceNotFoundException;
import pl.detectivegame.model.DetectiveCaseInfo;
import pl.detectivegame.model.DetectiveCaseInfoWithCreator;
import pl.detectivegame.model.Save;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.dashboard.AllDetectiveCasesResponse;
import pl.detectivegame.payload.gameplay.*;
import pl.detectivegame.repository.DetectiveCaseInfoRepository;
import pl.detectivegame.repository.DetectiveCaseWithCreatorNameRepository;
import pl.detectivegame.repository.SaveRepository;
import pl.detectivegame.repository.UserRepository;
import pl.detectivegame.util.DetectiveCaseMapper;

import java.util.Date;
import java.util.List;
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

    @Autowired
    private DetectiveCaseWithCreatorNameRepository detectiveCaseWithCreatorNameRepository;

    public DetectiveCaseInfoResponse createDetectiveCase(DetectiveCaseRequest detectiveCaseRequest) {
        DetectiveCaseInfo detectiveCaseInfo =
                DetectiveCaseInfo.builder()
                        .id(detectiveCaseRequest.getId())
                        .image(detectiveCaseRequest.getImage())
                        .name(detectiveCaseRequest.getName())
                        .time(detectiveCaseRequest.getTime())
                        .description(detectiveCaseRequest.getDescription())
                        .ready(detectiveCaseRequest.isReady())
                        .build();
        detectiveCaseInfo = detectiveCaseInfoRepository.save(detectiveCaseInfo);
        User creator = getCreator(detectiveCaseInfo);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCaseInfo, creator);
    }

    public DetectiveCaseInfoResponse getDetectiveCaseInfoById(Long detectiveCaseId) {
        DetectiveCaseInfo detectiveCaseInfo = detectiveCaseInfoRepository.findById(detectiveCaseId).orElseThrow(
                () -> new ResourceNotFoundException("Case", "id", detectiveCaseId));
        User creator = getCreator(detectiveCaseInfo);
        return DetectiveCaseMapper.mapDetectiveCasetoDetectiveCaseResponse(detectiveCaseInfo, creator);
    }

    private User getCreator(DetectiveCaseInfo detectiveCaseInfo) {
        return userRepository.findById(detectiveCaseInfo.getCreator())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", detectiveCaseInfo.getCreator()));
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

    public AllDetectiveCasesResponse getAllDetectiveCases() {
        List<DetectiveCaseInfoWithCreator> saves = detectiveCaseWithCreatorNameRepository.findAll();
        return AllDetectiveCasesResponse.builder().detectiveCaseList(saves).build();
    }
}
