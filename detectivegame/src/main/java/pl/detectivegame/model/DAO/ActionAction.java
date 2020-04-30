package pl.detectivegame.model.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action_action")
@Data
@NoArgsConstructor
public class ActionAction {

    @EmbeddedId
    ActionActionIdentity actionActionIdentity;
}
