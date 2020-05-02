package pl.detectivegame.model.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "action")
@Data
@NoArgsConstructor
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

    private Date bgnDate;

    @Column(name = "case_id")
    private Long caseId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="action_id")
    private List<ActionLocation> revealedLocations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="action_id")
    private List<ActionAction> revealedActions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="action_id")
    private List<ActionItem> revealedItems;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="location")
    private Location location;
}
