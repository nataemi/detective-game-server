package pl.detectivegame.model.DAO;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location_connection")
@Data
@NoArgsConstructor
public class LocationConnection {

    @EmbeddedId
    @JsonUnwrapped
    private LocationConnectionIdentity locationConnectionIdentity;

    private int time;
}
