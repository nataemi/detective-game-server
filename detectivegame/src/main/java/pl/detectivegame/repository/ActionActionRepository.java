package pl.detectivegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.detectivegame.model.DAO.Action;
import pl.detectivegame.model.DAO.ActionAction;

import java.util.List;

@Repository
public interface ActionActionRepository extends JpaRepository<ActionAction,Long> {

    List<ActionAction> findAllByActionActionIdentity_ActionIdOrActionActionIdentity_RevealedId(Long actionId, Long revealedId);

    List<ActionAction> findAllByActionActionIdentity_ActionId(Long actionId);

    List<ActionAction> findAllByActionActionIdentity_ActionIdAndActionActionIdentity_RevealedId(Long actionId, Long revealedId);
}
