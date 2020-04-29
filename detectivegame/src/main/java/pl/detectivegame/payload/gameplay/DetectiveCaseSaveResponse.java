package pl.detectivegame.payload.gameplay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetectiveCaseSaveResponse {

    String jsonSave;
}
