package pl.detectivegame.model.DAO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class LocationConnectionIdentity implements Serializable {

    @Column(name = "from_id")
    private long fromId;

    @Column(name = "to_id")
    private long toId;


}
