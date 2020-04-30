package pl.detectivegame.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Action {

    private Long actionId;

    private String name;

    private String description;

    private String image;

    private int time;

    private Date bgnDate;

    private List<Succesor> succesors;
}
