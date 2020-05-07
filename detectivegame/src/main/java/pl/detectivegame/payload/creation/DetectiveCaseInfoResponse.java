package pl.detectivegame.payload.creation;

import lombok.Builder;
import lombok.Data;
import pl.detectivegame.payload.user.UserSummaryResponse;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class DetectiveCaseInfoResponse {
    private Long id;
    private String description;
    private String name;
    private String image;
    private boolean ready;
    private int time;
    private int days;
    private int mpPerDay;
    private Date bgnDt;
    private UserSummaryResponse createdBy;
    private Instant creationDateTime;
    private Instant modifiedDateTime;
}
