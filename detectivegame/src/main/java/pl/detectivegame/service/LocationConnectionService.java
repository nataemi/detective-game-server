package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.Location;
import pl.detectivegame.model.DAO.LocationConnection;
import pl.detectivegame.payload.creation.LocationConnectionPayload;
import pl.detectivegame.payload.creation.LocationPayload;
import pl.detectivegame.repository.LocationConnectionRepository;
import pl.detectivegame.repository.LocationRepository;

@Service
public class LocationConnectionService {

    @Autowired
    LocationConnectionRepository locationConnectionRepository;


    public LocationConnectionPayload saveLocationConnection(LocationConnectionPayload locationConnectionPayload) {
        LocationConnection locationConnection = locationConnectionPayload.getLocationConnection();
        locationConnection = locationConnectionRepository.save(locationConnection);
        locationConnectionPayload.setLocationConnection(locationConnection);
        return locationConnectionPayload;
    }

    public void deleteLocationConnection(LocationConnectionPayload locationConnectionPayload) {
        LocationConnection locationConnection = locationConnectionPayload.getLocationConnection();
        locationConnectionRepository.delete(locationConnection);
    }
}
