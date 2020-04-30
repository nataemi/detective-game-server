package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationConnectionWithName {

    String from;
    String to;
    int cost;
}
