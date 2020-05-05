package pl.detectivegame.model.DAO;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action_action")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActionAction {

    @EmbeddedId
    ActionActionIdentity actionActionIdentity;
}
