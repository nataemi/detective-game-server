package pl.detectivegame.payload.dashboard;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.model.DetectiveCaseInfoWithCreatorName;

import java.util.List;

@Builder
@Data
public class AllDetectiveCasesResponse {

    List<DetectiveCaseInfoWithCreatorName> detectiveCaseList;
}
