package pl.detectivegame.util.mapper;

import pl.detectivegame.model.DAO.Location;
import pl.detectivegame.model.DAO.LocationConnection;
import pl.detectivegame.model.LocationConnectionWithName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationConnectionResponseMapper {

    public static List<LocationConnectionWithName> map(List<LocationConnection> locationConnections, List<Location> locations){

        Map<Long, String> locationIdToString = locations.stream().collect(
                Collectors.toMap(Location::getLocationId, Location::getName));
        List<LocationConnectionWithName> responseLocationConnections;
        responseLocationConnections = locationConnections.stream().map(locationConnection ->
            LocationConnectionWithName.builder()
                    .cost(locationConnection.getTime())
                    .from(locationIdToString.get(locationConnection.getLocationConnectionIdentity().getFromId()))
                    .to(locationIdToString.get(locationConnection.getLocationConnectionIdentity().getToId()))
                    .build()
        ).collect(Collectors.toList());

        return responseLocationConnections;
    }
}
