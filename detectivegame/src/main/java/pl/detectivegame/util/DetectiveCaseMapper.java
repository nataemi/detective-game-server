package pl.detectivegame.util;

import pl.detectivegame.model.DetectiveCase;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.UserSummary;

public class DetectiveCaseMapper {

    public static DetectiveCaseInfoResponse mapDetectiveCasetoDetectiveCaseResponse(DetectiveCase detectiveCase, User creator){
        DetectiveCaseInfoResponse detectiveCaseInfoResponse =
                DetectiveCaseInfoResponse.builder()
                    .createdBy(UserSummary.builder()
                            .id(creator.getId())
                            .name(creator.getUsername())
                            .username(creator.getUsername())
                            .build())
                    .creationDateTime(detectiveCase.getCreated())
                    .description(detectiveCase.getDescription())
                    .image(detectiveCase.getImage())
                    .ready(detectiveCase.isReady())
                    .time(detectiveCase.getTime())
                    .id(detectiveCase.getId())
                    .build();
        return detectiveCaseInfoResponse;
    }
}
