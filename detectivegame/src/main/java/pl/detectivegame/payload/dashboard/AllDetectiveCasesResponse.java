package pl.detectivegame.payload.dashboard;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.model.DetectiveCaseInfoWithCreator;

import java.util.List;

@Builder
@Data
public class AllDetectiveCasesResponse {

    List<DetectiveCaseInfoWithCreator> detectiveCaseList;
}
