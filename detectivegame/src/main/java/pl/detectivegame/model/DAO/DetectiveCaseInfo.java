package pl.detectivegame.model.DAO;

import lombok.*;
import pl.detectivegame.model.Audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "detective_case")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetectiveCaseInfo extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 2000)
    private String description;

    @Size(max = 200)
    private String image;

    private boolean ready;

    private int time;

    @Column(name = "frst_action_id")
    private long frstActionId;

    @Column(name = "bgn_date", nullable = false)
    private Timestamp bgnDate;
}
