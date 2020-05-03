package pl.detectivegame.payload.creation;

import lombok.Getter;
import lombok.Setter;
import pl.detectivegame.model.DAO.Question;


@Getter
@Setter
public class QuestionPayload {

    Question question;
}
