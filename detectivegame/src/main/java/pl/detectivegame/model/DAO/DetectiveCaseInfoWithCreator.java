package pl.detectivegame.model.DAO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.detectivegame.model.Audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "detective_case")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedNativeQuery(name = "DetectiveCaseInfoWithCreator.findByUserId",
        query = "SELECT d.id, d.created, d.modified, d.creator, d.description, d.image, d.name, d.ready, d.time, u.username FROM DETECTIVE_CASE D  JOIN USERS U on D.creator = U.id WHERE D.ID IN (SELECT CASE_ID FROM SAVE WHERE PLAYER = :userId and score = -1);",
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

    private int time;

    @ManyToOne
    @JoinColumn(name="creator", nullable=false, insertable = false, updatable = false)
    @JsonIgnore
    User user;

    private String username;

    private int score;

    @Transient
    public String getUsername(){
        return getUser().getUsername();
    };
}
