package pl.detectivegame.payload.dashboard;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.payload.creation.DetectiveCaseInfoResponse;

import java.util.List;

@Builder
@Data
public class AllDetectiveCasesResponse {

    List<DetectiveCaseInfoResponse> detectiveCaseList;
}
