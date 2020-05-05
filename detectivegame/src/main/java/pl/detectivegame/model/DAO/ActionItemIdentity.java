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
@NoArgsConstructor
@AllArgsConstructor
public class ActionItemIdentity implements Serializable {

    @Column(name = "action_id")
    private long actionId;

    @Column(name = "item_id")
    private long itemId;


}
