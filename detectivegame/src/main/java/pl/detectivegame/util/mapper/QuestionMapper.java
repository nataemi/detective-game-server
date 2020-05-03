package pl.detectivegame.util.mapper;

import pl.detectivegame.model.DAO.Question;
import pl.detectivegame.model.DAO.QuestionWithoutAnswers;

public class QuestionMapper {

    public static QuestionWithoutAnswers mapWithoutQuestionId(Question question){
        QuestionWithoutAnswers questionWithoutAnswers =
                QuestionWithoutAnswers.builder()
                        .caseId(question.getCaseId())
                        .content(question.getContent())
                        .build();
        return questionWithoutAnswers;
    }

    public static QuestionWithoutAnswers mapWithQuestionId(Question question){
        QuestionWithoutAnswers questionWithoutAnswers =
        QuestionWithoutAnswers.builder()
                .questionId(question.getQuestionId())
                .caseId(question.getCaseId())
                .content(question.getContent())
                .build();
        return questionWithoutAnswers;
    }
}
