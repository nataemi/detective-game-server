package pl.detectivegame.model.DAO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ActionActionIdentity implements Serializable {

    @Column(name = "action_id")
    private long actionId;

    @Column(name = "revealed_id")
    private long revealedId;


}
