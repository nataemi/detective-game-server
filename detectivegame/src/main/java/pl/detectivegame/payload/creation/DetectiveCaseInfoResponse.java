package pl.detectivegame.payload.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import pl.detectivegame.payload.user.UserSummaryResponse;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
public class DetectiveCaseInfoResponse {
    @JsonProperty(value = "case_id")
    private Long id;
    private String description;
    private String name;
    private String image;
    private boolean ready;
    private int time;
    private int days;
    private UserSummaryResponse createdBy;
    private Instant creationDateTime;
    private Instant modifiedDateTime;
    @JsonProperty(value = "max_days")
    private int maxDays;

    @JsonProperty(value = "mp_per_day")
    private int mpPerDay;

    @JsonProperty(value = "bgn_dt")
    private Date bgnDt;

    @JsonProperty(value = "frst_action_id")
    private Long frstActionId;
}
