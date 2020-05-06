package pl.detectivegame.payload.creation;

import lombok.Builder;
import lombok.Getter;
import pl.detectivegame.model.VerficiationStatus;

import java.util.List;

@Getter
@Builder
public class ValidatePayload {

    private VerficiationStatus status;
    private List<String> wrongList;
    private List<String> warningList;

}
