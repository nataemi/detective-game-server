package pl.detectivegame.model.DAO;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "save")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long save_id;

    @NotNull
    private Long player;

    @NotNull
    @Column(name = "case_id", nullable = false)
    private Long caseId;

    @NotNull
    @Column(name = "last_modified", nullable = false)
    private Date lastModified = new Date();

    private String save_json;

    private int score;


}

