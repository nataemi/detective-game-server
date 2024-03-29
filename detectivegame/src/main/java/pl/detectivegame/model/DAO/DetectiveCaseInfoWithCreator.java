package pl.detectivegame.model.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.detectivegame.model.Audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedNativeQuery(name = "DetectiveCaseInfoWithCreator.findByUserId",
        query = "SELECT d.id, d.created, d.modified, d.creator, d.description, d.image, d.name, d.ready, d.max_days, d.mp_per_day, u.username, s.score FROM DETECTIVE_CASE D JOIN USERS U on D.creator = U.id  JOIN SAVE S ON s.case_id = d.id WHERE S.player = :userId and S.score = -1;\n;",
        resultClass = DetectiveCaseInfoWithCreator.class)
public class DetectiveCaseInfoWithCreator extends UserDateAudit {

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

    @Column(name = "max_days")
    private int maxDays;

    @Column(name = "mp_per_day")
    private int mpPerDay;

    private String username;

    private int score;
}
