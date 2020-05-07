package pl.detectivegame.payload.creation;

import lombok.Getter;

import java.util.Date;

@Getter
public class DetectiveCaseInfoRequest {

    private Long id;

    private String name;

    private String description;

    private String image;

    private boolean ready;

    private int maxDays;

    private int mpPerDay;

    private Date bgnDt;


}
