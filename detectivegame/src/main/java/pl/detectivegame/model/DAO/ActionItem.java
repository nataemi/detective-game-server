package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action_item")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ActionItem {

    @EmbeddedId
    ActionItemIdentity actionItemIdentity;
}
