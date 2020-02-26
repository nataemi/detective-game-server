package pl.detectivegame.payload;

import lombok.Getter;

@Getter
public class DetectiveCaseRequest {

    private Long id;

    private String name;

    private String description;

    private String image;

    private boolean ready;

    private int time;
}
