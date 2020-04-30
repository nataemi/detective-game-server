package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.Action;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {

    List<Action> findByCaseId(Long caseId);
}
