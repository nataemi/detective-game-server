package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.Location;
import pl.detectivegame.payload.creation.LocationPayload;
import pl.detectivegame.repository.LocationRepository;
import pl.detectivegame.util.mapper.LocationMapper;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;


    public LocationPayload saveLocation(LocationPayload locationPayload) {
        Location location = locationPayload.getLocation();
        pl.detectivegame.model.DAO.Location locationDAO = LocationMapper.map(location);
        locationDAO = locationRepository.save(locationDAO);
        locationPayload.setLocation(LocationMapper.map(locationDAO));
        return locationPayload;
    }


    public void deleteLocation(LocationPayload locationPayload) {
        Location location = locationPayload.getLocation();
        pl.detectivegame.model.DAO.Location locationDAO = LocationMapper.map(location);
        locationRepository.delete(locationDAO);
    }
}
