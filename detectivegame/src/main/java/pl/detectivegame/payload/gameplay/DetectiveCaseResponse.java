package pl.detectivegame.payload.gameplay;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.model.NewDetectiveCase;

@Builder
@Data
public class DetectiveCaseResponse {

    NewDetectiveCase newDetectiveCase;
}
