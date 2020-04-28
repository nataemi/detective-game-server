package pl.detectivegame.payload;

import lombok.Builder;
import lombok.Data;

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
    private UserSummary createdBy;
    private Instant creationDateTime;
}
