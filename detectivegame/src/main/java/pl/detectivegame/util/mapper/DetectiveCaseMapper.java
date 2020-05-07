package pl.detectivegame.util.mapper;

import pl.detectivegame.model.DAO.DetectiveCaseInfo;
import pl.detectivegame.model.DAO.User;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;
import pl.detectivegame.payload.user.UserSummaryResponse;

public class DetectiveCaseMapper {

    public static DetectiveCaseInfoResponse mapDetectiveCasetoDetectiveCaseResponse(DetectiveCaseInfo detectiveCaseInfo, User creator) {
        DetectiveCaseInfoResponse detectiveCaseInfoResponse =
                DetectiveCaseInfoResponse.builder()
                        .createdBy(UserSummaryResponse.builder()
                                .id(creator.getId())
                                .name(creator.getUsername())
                                .username(creator.getUsername())
                                .build())
                        .creationDateTime(detectiveCaseInfo.getCreated())
                        .modifiedDateTime(detectiveCaseInfo.getModified())
                        .description(detectiveCaseInfo.getDescription())
                        .image(detectiveCaseInfo.getImage())
                        .ready(detectiveCaseInfo.isReady())
                        .time(detectiveCaseInfo.getMpPerDay() * detectiveCaseInfo.getMaxDays())
                        .mpPerDay(detectiveCaseInfo.getMpPerDay())
                        .days(detectiveCaseInfo.getMaxDays())
                        .name(detectiveCaseInfo.getName())
                        .id(detectiveCaseInfo.getId())
                        .bgnDt(detectiveCaseInfo.getBgnDate())
                        .build();
        return detectiveCaseInfoResponse;
    }
}
