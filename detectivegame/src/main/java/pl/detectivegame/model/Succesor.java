package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Succesor {

    private Long id;
    private SuccesorType type;
}
