package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.QuestionWithoutAnswers;

@Repository
public interface QuestionWithoutAnswersRepository extends JpaRepository<QuestionWithoutAnswers,Long> {

}
