package pl.detectivegame.payload.user;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.model.DetectiveCaseInfoWithCreator;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;

import java.util.List;

@Builder
@Data
public class AllDetectiveCasesResponse {

    List<DetectiveCaseInfoWithCreator> detectiveCaseList;
}
