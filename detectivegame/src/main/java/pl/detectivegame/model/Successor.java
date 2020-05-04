package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Successor {

    private Long id;
    private String type;
}
