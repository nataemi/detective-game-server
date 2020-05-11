package pl.detectivegame.model.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class LocationConnectionIdentity implements Serializable {

    @Column(name = "from_id")
    @JsonProperty("location1")
    private long fromId;

    @Column(name = "to_id")
    @JsonProperty("location2")
    private long toId;


}
