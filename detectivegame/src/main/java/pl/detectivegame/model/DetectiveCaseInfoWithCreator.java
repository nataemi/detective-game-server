package pl.detectivegame.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.detectivegame.model.Audit.UserDateAudit;
import pl.detectivegame.model.DAO.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetectiveCaseInfoWithCreator extends UserDateAudit {


    private Long id;


    private String name;

    private String description;

    private String image;

    private boolean ready;

    @JsonProperty(value = "max_days")
    private int maxDays;

    @JsonProperty(value = "mp_per_day")
    private int mpPerDay;

    private String username;

    private int score;

    @JsonProperty(value = "movement_points")
    private int movementPoints;
}
