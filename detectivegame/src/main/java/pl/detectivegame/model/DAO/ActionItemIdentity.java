package pl.detectivegame.model.DAO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ActionItemIdentity implements Serializable {

    @Column(name = "action_id")
    private long actionId;

    @Column(name = "item_id")
    private long itemId;


}
