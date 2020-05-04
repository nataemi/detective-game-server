package pl.detectivegame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Item {

    @JsonProperty("id")
    private Long itemId;

    @NotBlank
    private String name;

    private String description;

    private String image;

    private String examineInfo;

    private boolean revealed;

    private boolean examined;

}
