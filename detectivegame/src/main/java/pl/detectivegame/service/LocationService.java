package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.Location;
import pl.detectivegame.payload.creation.LocationPayload;
import pl.detectivegame.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;


    public LocationPayload saveLocation(LocationPayload locationPayload) {
        Location location = locationPayload.getLocation();
        location = locationRepository.save(location);
        locationPayload.setLocation(location);
        return locationPayload;
    }


    public void deleteLocation(LocationPayload locationPayload) {
        Location location = locationPayload.getLocation();
        locationRepository.delete(location);
    }
}
