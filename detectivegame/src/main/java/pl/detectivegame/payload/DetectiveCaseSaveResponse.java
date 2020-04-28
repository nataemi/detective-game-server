package pl.detectivegame.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetectiveCaseSaveResponse {

    String jsonSave;
}
