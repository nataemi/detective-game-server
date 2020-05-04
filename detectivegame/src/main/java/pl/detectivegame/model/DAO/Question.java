package pl.detectivegame.model.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    @JsonProperty("question_id")
    private Long questionId;

    @Column(name = "case_id")
    @JsonProperty("case_id")
    private Long caseId;

    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    List<Answer> answers;
}

