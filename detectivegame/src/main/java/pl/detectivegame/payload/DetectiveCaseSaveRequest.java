package pl.detectivegame.payload;

import lombok.Data;

@Data
public class DetectiveCaseSaveRequest {

    Long caseId;
    Long userId;
}
