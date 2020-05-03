package pl.detectivegame.payload.creation;

import lombok.Getter;
import lombok.Setter;
import pl.detectivegame.model.DAO.LocationConnection;

@Getter
@Setter
public class LocationConnectionPayload {
    LocationConnection locationConnection;
}
