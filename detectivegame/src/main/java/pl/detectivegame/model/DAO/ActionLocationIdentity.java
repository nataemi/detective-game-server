package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionLocationIdentity implements Serializable {

    @Column(name = "action_id")
    private long actionId;

    @Column(name = "location_id")
    private long locationId;


}
