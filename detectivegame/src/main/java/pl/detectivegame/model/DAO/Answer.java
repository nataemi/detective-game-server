package pl.detectivegame.model.DAO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Data
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    @JsonProperty("answer_id")
    private Long answerId;

    private String content;

    private boolean correct;

    @Column(name="question_id")
    @JsonProperty("question_id")
    private Long questionId;
}
