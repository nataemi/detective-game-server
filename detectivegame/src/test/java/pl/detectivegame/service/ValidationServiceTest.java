package pl.detectivegame.service;

import org.junit.jupiter.api.Test;
import pl.detectivegame.model.DAO.Answer;
import pl.detectivegame.model.DAO.Question;
import pl.detectivegame.model.NewDetectiveCase;

import java.util.Collections;
import java.util.List;


class ValidationServiceTest {


    //nothing declared except maxScore
    @Test
    void validateDetectiveCaseNothingDeclared() {
        ValidationService validationService = new ValidationService();
        NewDetectiveCase newDetectiveCase = NewDetectiveCase.builder().maxScore(10).build();
        validationService.validateDetectiveCase(newDetectiveCase);
    }

    @Test
    void validateDetectiveCaseQuestonWithoutAnswers() {
        ValidationService validationService = new ValidationService();
        Question question = new Question();
        NewDetectiveCase newDetectiveCase = NewDetectiveCase.builder().test(Collections.singletonList(question)).build();
        validationService.validateDetectiveCase(newDetectiveCase);
    }

    @Test
    void validateDetectiveCaseQuestonWithoutCorrectAnswers() {
        ValidationService validationService = new ValidationService();
        Question question = new Question();
        Answer answer = new Answer();
        answer.setCorrect(false);
        question.setAnswers(Collections.singletonList(answer));
        NewDetectiveCase newDetectiveCase = NewDetectiveCase.builder().test(Collections.singletonList(question)).build();
        validationService.validateDetectiveCase(newDetectiveCase);
    }
}
