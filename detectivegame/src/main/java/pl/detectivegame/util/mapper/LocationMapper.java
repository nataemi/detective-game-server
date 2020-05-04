package pl.detectivegame.util.mapper;

import pl.detectivegame.model.DAO.Location;

public class LocationMapper {

    public static pl.detectivegame.model.Location map(Location location){
        return  pl.detectivegame.model.Location.builder()
                .caseId(location.getCaseId())
                .description(location.getDescription())
                .image(location.getImage())
                .name(location.getName())
                .revealed(false)
                .isStart(location.isStart())
                .locationId(location.getLocationId())
                .build();
    }

    public static Location map(pl.detectivegame.model.Location location){
        return  Location.builder()
                .caseId(location.getCaseId())
                .description(location.getDescription())
                .image(location.getImage())
                .name(location.getName())
                .isStart(location.isStart())
                .locationId(location.getLocationId())
                .build();
    }
}
