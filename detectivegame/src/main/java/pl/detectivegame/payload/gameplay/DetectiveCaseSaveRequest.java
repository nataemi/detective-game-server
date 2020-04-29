package pl.detectivegame.payload.gameplay;

import lombok.Data;

@Data
public class DetectiveCaseSaveRequest {

    Long caseId;
    Long userId;
}
