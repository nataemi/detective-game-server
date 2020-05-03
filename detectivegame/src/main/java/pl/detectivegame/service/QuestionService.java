package pl.detectivegame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.DAO.Answer;
import pl.detectivegame.model.DAO.Question;
import pl.detectivegame.model.DAO.QuestionWithoutAnswers;
import pl.detectivegame.payload.creation.QuestionPayload;
import pl.detectivegame.repository.AnswerRepository;
import pl.detectivegame.repository.QuestionRepository;
import pl.detectivegame.repository.QuestionWithoutAnswersRepository;
import pl.detectivegame.util.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionWithoutAnswersRepository questionWithoutAnswersRepostiory;

    @Autowired
    AnswerRepository answerRepository;


    public QuestionPayload createQuestion(QuestionPayload questionPayload) {
        Question question = questionPayload.getQuestion();
        QuestionWithoutAnswers questionWithoutAnswers = QuestionMapper.mapWithoutQuestionId(question);
        questionWithoutAnswers = questionWithoutAnswersRepostiory.save(questionWithoutAnswers);
        updateAnswers(question, questionWithoutAnswers);
        questionPayload.setQuestion(question);
        return questionPayload;
    }

    public QuestionPayload updateQuestion(QuestionPayload questionPayload) {
        Question question = questionPayload.getQuestion();
        QuestionWithoutAnswers questionWithoutAnswers = QuestionMapper.mapWithQuestionId(question);
        questionWithoutAnswers = questionWithoutAnswersRepostiory.save(questionWithoutAnswers);
        deleteUnusedAnswers(question);
        updateAnswers(question, questionWithoutAnswers);
        questionPayload.setQuestion(question);
        return questionPayload;
    }

    private void updateAnswers(Question question, QuestionWithoutAnswers questionWithoutAnswers) {
        List<Answer> answers = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {
            answer.setQuestionId(questionWithoutAnswers.getQuestionId());
            answer = answerRepository.save(answer);
            answers.add(answer);
        }
        question.setQuestionId(questionWithoutAnswers.getQuestionId());
        question.setAnswers(answers);
    }

    private void deleteAnswers(Question question) {
        for (Answer answer : question.getAnswers()) {
            answerRepository.delete(answer);
        }
    }

    private void deleteUnusedAnswers(Question question) {
        List<Answer> answersInDb = answerRepository.findByQuestionId(question.getQuestionId());
        Set<Long> answerRequestIds = question.getAnswers().stream().map(answer -> answer.getAnswerId()).collect(Collectors.toSet());
        for (Answer answerInDb : answersInDb){
            if (!answerRequestIds.contains(answerInDb.getAnswerId())) answerRepository.delete(answerInDb);
        }
    }

    public void deleteQuestion(QuestionPayload questionPayload) {
        Question question = questionPayload.getQuestion();
        deleteAnswers(question);
        QuestionWithoutAnswers questionWithoutAnswers = QuestionMapper.mapWithQuestionId(question);
        questionWithoutAnswersRepostiory.delete(questionWithoutAnswers);
    }
}
