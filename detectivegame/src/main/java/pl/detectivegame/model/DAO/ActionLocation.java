package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionLocation {

    @EmbeddedId
    ActionLocationIdentity actionLocationIdentity;
}
