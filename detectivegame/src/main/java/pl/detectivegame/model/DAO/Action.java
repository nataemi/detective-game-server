package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="action_id")
    private Long actionId;

    @NotBlank
    private String name;

    private String description;

    private String image;

    private int time;

    @Column(name = "case_id")
    private Long caseId;

    @OneToMany()
    @JoinColumn(name="action_id")
    private List<ActionLocation> revealedLocations;

    @OneToMany()
    @JoinColumn(name="action_id")
    private List<ActionAction> revealedActions;

    @OneToMany()
    @JoinColumn(name="action_id")
    private List<ActionItem> revealedItems;

    @OneToOne()
    @JoinColumn(name="location")
    private Location location;
}
