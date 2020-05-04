package pl.detectivegame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @JsonProperty("id")
    private Long locationId;

    @JsonProperty("case_id")
    private Long caseId;

    private String name;

    private String description;

    private String image;

    private boolean revealed;

    @JsonIgnore
    private boolean isStart;
}

