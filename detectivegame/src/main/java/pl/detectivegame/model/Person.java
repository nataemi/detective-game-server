package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Person {

    private Long personId;

    @NotBlank
    private String fullName;

    private String description;

    private String image;

    private boolean revealed;


}
