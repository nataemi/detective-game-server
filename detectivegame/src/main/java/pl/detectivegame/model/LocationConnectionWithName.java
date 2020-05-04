package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationConnectionWithName {

    String location1;
    String location2;
    int cost;
}
