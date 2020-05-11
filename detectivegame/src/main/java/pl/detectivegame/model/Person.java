package pl.detectivegame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Person {

    @JsonProperty("id")
    private Long personId;

    @NotBlank
    private String fullname;

    private String description;

    private String image;

    private boolean revealed;

    @JsonProperty("case_id")
    private long caseId;
}
