package pl.detectivegame.payload.creation;

import lombok.Builder;
import lombok.Getter;
import pl.detectivegame.model.VerficationStatus;

import java.util.List;

@Getter
@Builder
public class ValidatePayload {

    private VerficationStatus status;
    private List<String> wrongList;
    private List<String> warningList;

}
