package pl.detectivegame.model.DAO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ActionLocationIdentity implements Serializable {

    @Column(name = "action_id")
    private long actionId;

    @Column(name = "location_id")
    private long locationId;


}
