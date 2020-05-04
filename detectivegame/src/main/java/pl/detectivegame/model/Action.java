package pl.detectivegame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Action {

    @JsonProperty("id")
    private Long actionId;

    private String name;

    private String description;

    private String image;

    private int time;

    private List<Successor> successors;

    private String location;

    private boolean revealed;

    private boolean done;

    private String caseId;

}
