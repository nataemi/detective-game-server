package pl.detectivegame.payload.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class DetectiveCaseInfoRequest {

    @JsonProperty(value = "case_id")
    private Long id;

    private String name;

    private String description;

    private String image;

    private boolean ready;

    @JsonProperty(value = "max_days")
    private int maxDays;

    @JsonProperty(value = "mp_per_day")
    private int mpPerDay;

    @JsonProperty(value = "bgn_dt")
    private Date bgnDt;

    @JsonProperty(value = "frst_action_id")
    private Long frstActionId;


}
