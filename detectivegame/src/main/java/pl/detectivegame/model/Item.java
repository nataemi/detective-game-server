package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Item {

    private Long itemId;

    @NotBlank
    private String name;

    private String description;

    private String image;

    private String examineInfo;

    private boolean revealed;

    private boolean examined;

}
