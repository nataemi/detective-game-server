package pl.detectivegame.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OptimalPath {
    String location1;
    String location2;
    List<Integer> optimalPath;
    Double cost;
}
