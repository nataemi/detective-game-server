package pl.detectivegame.util;

import pl.detectivegame.model.DetectiveCaseInfo;
import pl.detectivegame.model.User;
import pl.detectivegame.payload.gameplay.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.user.UserSummaryResponse;

public class DetectiveCaseMapper {

    public static DetectiveCaseInfoResponse mapDetectiveCasetoDetectiveCaseResponse(DetectiveCaseInfo detectiveCaseInfo, User creator){
        DetectiveCaseInfoResponse detectiveCaseInfoResponse =
                DetectiveCaseInfoResponse.builder()
                    .createdBy(UserSummaryResponse.builder()
                            .id(creator.getId())
                            .name(creator.getUsername())
                            .username(creator.getUsername())
                            .build())
                    .creationDateTime(detectiveCaseInfo.getCreated())
                    .description(detectiveCaseInfo.getDescription())
                    .image(detectiveCaseInfo.getImage())
                    .ready(detectiveCaseInfo.isReady())
                    .time(detectiveCaseInfo.getTime())
                    .id(detectiveCaseInfo.getId())
                    .build();
        return detectiveCaseInfoResponse;
    }
}
