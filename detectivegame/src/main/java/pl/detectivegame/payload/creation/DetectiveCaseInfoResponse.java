package pl.detectivegame.payload.creation;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.payload.user.UserSummaryResponse;

import java.time.Instant;

@Data
@Builder
public class DetectiveCaseInfoResponse {
    private Long id;
    private String description;
    private String name;
    private String image;
    private boolean ready;
    private int time;
    private UserSummaryResponse createdBy;
    private Instant creationDateTime;
    private Instant modifiedDateTime;
}
